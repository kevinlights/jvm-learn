package com.gjh.learn.jvm.clazz;

import com.gjh.learn.jvm.utils.Print;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * created on 2021/3/31
 *
 * @author kevinlights
 */
public class FindClassOrder2 {
    public static void main(String[] args) throws Exception {
        ClassLoader cl = FindClassOrder2.class.getClassLoader();
        byte[] bLoader = loadClassBytes("com.gjh.learn.jvm.clazz.HelloLoader");
        Method m = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        m.setAccessible(true);
        m.invoke(cl, bLoader, 0, bLoader.length);
        m.setAccessible(false);

        // 已经被加载到应用类加载器中了，即使设置了 -Xbootclasspath 不会再由启动类加载器加载
        HelloLoader loader = new HelloLoader();
        Print.GREEN(loader.getClass().getClassLoader());
        loader.print();
    }

    private static byte[] loadClassBytes(String className) throws Exception {
        File directory = new File("");
        String absolutePath = directory.getAbsolutePath();
        String classAsPath = absolutePath + "/target/classes/" + className.replace('.', '/') + ".class";
        // String classAsPath = "E:\\Study\\Java\\jvm\\jvm-learn\\clz\\com\\gjh\\learn\\jvm\\clazz\\HelloLoader.class";
        InputStream is = new FileInputStream(classAsPath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len;
        while ((len = is.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }
        return baos.toByteArray();
    }
}
