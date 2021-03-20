package com.gjh.learn.jvm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * created on 2021/3/20
 *
 * [Loaded Example from __JVM_DefineClass__]
 * [Loaded Example from __JVM_DefineClass__]
 * [Unloading class Example 0x00000007c0061028]
 * [Loaded Example from __JVM_DefineClass__]
 * [Unloading class Example 0x00000007c0061828]
 * [Loaded Example from __JVM_DefineClass__]
 * [Unloading class Example 0x00000007c0061028]
 * [Loaded Example from __JVM_DefineClass__]
 * [Unloading class Example 0x00000007c0061828]
 * [Loaded Example from __JVM_DefineClass__]
 * [Unloading class Example 0x00000007c0061028]
 * [Loaded Example from __JVM_DefineClass__]
 * [Unloading class Example 0x00000007c0061828]
 * [Loaded Example from __JVM_DefineClass__]
 * [Unloading class Example 0x00000007c0061028]
 * [Loaded Example from __JVM_DefineClass__]
 * [Unloading class Example 0x00000007c0061828]
 * [Loaded Example from __JVM_DefineClass__]
 * [Unloading class Example 0x00000007c0061028]
 *
 * 10 次加载，9 次卸载，最后一次加载的类没有机会被卸载
 * @author kevinlights
 */
public class UnloadClass implements Opcodes {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_7, ACC_PUBLIC, "Example", null, "java/lang/Object", null);
        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mw.visitVarInsn(ALOAD, 0);
        mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(0, 0);
        mw.visitEnd();

        mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mw.visitLdcInsn("Hello World!");
        mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintSteam", "println", "(Ljava/lang/String;)V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(0, 0);
        mw.visitEnd();

        byte[] code = cw.toByteArray();
        for (int i = 0; i < 10; i++) {
            UnloadClassLoader loader = new UnloadClassLoader();
            Method m = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            m.setAccessible(true);
            m.invoke(loader, "Example", code, 0, code.length);
            m.setAccessible(false);
            System.gc();
        }
    }
}
