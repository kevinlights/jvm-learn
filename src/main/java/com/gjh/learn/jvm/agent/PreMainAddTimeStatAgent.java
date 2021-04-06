package com.gjh.learn.jvm.agent;

import com.gjh.learn.jvm.asm.TimeStatClassAdapter;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class PreMainAddTimeStatAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs: " + agentArgs);
        inst.addTransformer(((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            if (className.equals("com/gjh/learn/jvm/asm/SlowTask")) {
                System.out.println("meet com.gjh.learn.jvm.asm.SlowTask");
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
                return classfileBuffer;
            }
        }));
    }
}
