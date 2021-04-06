package com.gjh.learn.jvm.agent;

import java.lang.instrument.Instrumentation;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class PreMainTraceAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs: " + agentArgs);
        inst.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            System.out.println("load class: " + className);
            return classfileBuffer;
        });
    }
}
