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
public class FindClassOrder3 {
    public static void main(String[] args) throws Exception {
        ClassLoader cl = FindClassOrder3.class.getClassLoader();
        byte[] bLoader = loadClassBytes("com.gjh.learn.jvm.clazz.HelloLoader");
        Method m = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        m.setAccessible(true);
        m.invoke(cl, bLoader, 0, bLoader.length);
        m.setAccessible(false);

        // 从扩展类加载器中加载的类是启动版本的，虽然在扩展类加载之前，该类已经在应用类加载器中了，但是扩展类只会向上询问，而不会向下，如果设置了 -Xbootclasspath ，则该类会重新由 启动类加载器 加载
        // 如果不加 -Xbootclasspath 则会找不到类
        Object loader = cl.getParent().loadClass("com.gjh.learn.jvm.clazz.HelloLoader").newInstance();
        Print.GREEN(loader.getClass().getClassLoader());
        Method m2 = loader.getClass().getMethod("print", null);
        m2.invoke(loader, null);
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
