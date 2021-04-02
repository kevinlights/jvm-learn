package com.gjh.learn.jvm.asm;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * created on 2021/4/2
 *
 * @author kevinlights
 */
public class SecurityWeaveGenerator {
    public static void main(String[] args) throws IOException {
        String className = Account.class.getName();
        ClassReader cr = new ClassReader(className);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        AddSecurityCheckClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] codeData = cw.toByteArray();

        // create directory first
        File dir = new File("target/" + className.substring(0, className.lastIndexOf(".")).replaceAll("\\.", "/"));
        dir.mkdirs();
        File file = new File("target/" + className.replaceAll("\\.", "/") + ".class");

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(codeData);
        fos.close();
    }
}
