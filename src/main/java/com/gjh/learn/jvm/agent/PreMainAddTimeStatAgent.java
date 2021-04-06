package com.gjh.learn.jvm.agent;

import com.gjh.learn.jvm.asm.Account;
import com.gjh.learn.jvm.asm.SlowTask;
import com.gjh.learn.jvm.asm.TimeStatClassAdapter;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class PreMainAddTimeStatAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("PreMainAddTimeStatAgent -> premain -> agentArgs: " + agentArgs);
        inst.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            // System.out.println("PreMainAddTimeStatAgent -> premain -> transform: " + className);
            if (className.equals("com/gjh/learn/jvm/asm/SlowTask")) {
                System.out.println("PreMainAddTimeStatAgent -> meet com.gjh.learn.jvm.asm.SlowTask");
                ClassReader cr;
                try {
                    cr = new ClassReader(className);
                    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                    TimeStatClassAdapter adapter = new TimeStatClassAdapter(cw);
                    cr.accept(adapter, ClassReader.SKIP_DEBUG);
                    return cw.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                    return classfileBuffer;
                }
            } else {
                // System.out.println("PreMainAddTimeStatAgent -> premain: " + className);
                return classfileBuffer;
            }
            // canRetransform should be set to true
        }, true);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException, ClassNotFoundException {
        System.out.println("PreMainAddTimeStatAgent -> Agent Main Called.");
        System.out.println("PreMainAddTimeStatAgent -> agentmain -> agentArgs: " + agentArgs);
        premain(agentArgs, inst);
        inst.retransformClasses(Class.forName(agentArgs));
    }
}
