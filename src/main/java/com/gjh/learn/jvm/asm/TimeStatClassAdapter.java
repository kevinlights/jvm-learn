package com.gjh.learn.jvm.asm;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class TimeStatClassAdapter extends ClassVisitor {
    public TimeStatClassAdapter(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        MethodVisitor mv = cv.visitMethod(i, s, s1, s2, strings);
        MethodVisitor wrappedMv = mv;
        if (mv != null && s.equals("operation")) {
            wrappedMv = new TimeStatMethodAdapter(mv);
        }
        return wrappedMv;
    }
}
