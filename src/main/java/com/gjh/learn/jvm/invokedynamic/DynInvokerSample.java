package com.gjh.learn.jvm.invokedynamic;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Handle;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.commons.GeneratorAdapter;
import jdk.internal.org.objectweb.asm.commons.Method;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class DynInvokerSample extends ClassLoader{
    private static final Handle BSM = new Handle(H_INVOKESTATIC, DynBootStrap.class.getName().replace('.', '/'), "bootstrap", MethodType.methodType(CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class, Object.class).toMethodDescriptorString());

    public Class createClass() throws IOException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_7, ACC_PUBLIC | ACC_SUPER, "DynInvokerSampleMain", null, "java/lang/Object", null);
        Method m = Method.getMethod("void <init> ()");
        GeneratorAdapter mg = new GeneratorAdapter(ACC_PUBLIC, m, null, null, cw);
        mg.loadThis();
        mg.invokeConstructor(Type.getType(Object.class), m);
        mg.returnValue();
        mg.endMethod();

        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "run", "()V", null, null);
        mv.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitInvokeDynamicInsn("hashCode", "()I", BSM, "guang");
        // set last param to false
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        FileOutputStream fos = new FileOutputStream("E:\\Study\\Java\\jvm\\jvm-learn\\clz\\DynInvokerSampleMain.class");
        fos.write(bytes);
        return this.defineClass("DynInvokerSampleMain", bytes, 0, bytes.length);
    }

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        DynInvokerSample me = new DynInvokerSample();
        Object obj = me.createClass().newInstance();
        obj.getClass().getMethod("run").invoke(null);
        System.out.println("guang".hashCode());
    }
}
