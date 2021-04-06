package com.gjh.learn.jvm.agent;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class AttachToolMain {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException, InterruptedException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            if (vmd.displayName().endsWith("RunLoopSlowTaskMain")) {
                System.out.println("start attach to RunLoopSlowTaskMain...");
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                virtualMachine.loadAgent("E:/Study/Java/jvm/jvm-learn/target/jvm-learn-1.0-SNAPSHOT.jar", "com.gjh.learn.jvm.asm.SlowTask");
                System.out.println("ok");
                virtualMachine.detach();
            }
        }
    }
}
