package com.gjh.learn.jvm.asm;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class TimeStatWeaveGenerator {
    public static void main(String[] args) throws IOException {
        String className = SlowTask.class.getName();
        ClassReader cr = new ClassReader(className);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        TimeStatClassAdapter adapter = new TimeStatClassAdapter(cw);
        cr.accept(adapter, ClassReader.SKIP_DEBUG);
        byte[] codeData = cw.toByteArray();
        File file = new File("target/classes/" + className.replaceAll("\\.", "/") + ".class");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(codeData);
        fos.close();
    }
}
