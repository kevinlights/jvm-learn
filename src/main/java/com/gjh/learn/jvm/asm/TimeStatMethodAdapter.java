package com.gjh.learn.jvm.asm;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class TimeStatMethodAdapter extends MethodVisitor {
    public TimeStatMethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/gjh/learn/jvm/asm/TimeStat", "start", "()V", true);
        super.visitCode();
    }

    @Override
    public void visitInsn(int i) {
        if ((i >= Opcodes.IRETURN && i <= Opcodes.RETURN)) {
            visitMethodInsn(Opcodes.INVOKESTATIC, "com/gjh/learn/jvm/asm/TimeStat", "end", "()V", true);
        }
        super.visitInsn(i);
    }
}
