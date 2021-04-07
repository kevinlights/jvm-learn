package com.gjh.learn.jvm.jit;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.lang.reflect.Method;


/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class CacheCodeJIT implements Opcodes {
    public static void createAndCall(String hellostr, String className) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_7, ACC_PUBLIC, className, null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn(hellostr);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] bytes = cw.toByteArray();

        JitClassLoader loader = new JitClassLoader();
        Method m;
        try {
            m = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            m.setAccessible(true);
            Class examClz = (Class) m.invoke(loader, className, bytes, 0, bytes.length);
            examClz.getMethods()[0].invoke(null, new Object[]{null});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            createAndCall("hello,world" + i, "Ex" + i);
            // 停止 JIT 后再 GC， JIT 不会再启用了
            if (i >= 25000) {
                System.gc();
            }
        }
    }
}
