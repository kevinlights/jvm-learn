package com.gjh.learn.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class MyClassLoader extends ClassLoader{
    public static final String CLASS_NAME = "com.gjh.learn.jvm.classloader.DemoA";
    private String path;

    public MyClassLoader(String path) {
        this.path = path;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("MyClassLoader -> loadClass: " + name);
        if (name.equals(CLASS_NAME)) {
            return findClass(name);
        } else {
            return super.loadClass(name);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("MyClassLoader -> findClass: " + name);
        Class<?> clazz = this.findLoadedClass(name);
        if (null == clazz) {
            try {
                System.out.println("result of findLoadedClass is null");
                String classFile = getClassFile(name);
                FileInputStream fis = new FileInputStream(classFile);
                FileChannel fileChannel = fis.getChannel();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                WritableByteChannel wbc = Channels.newChannel(baos);
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                while (true) {
                    int read = fileChannel.read(buffer);
                    if (read < 1) {
                        break;
                    }
                    buffer.flip();
                    wbc.write(buffer);
                    buffer.clear();
                }
                fis.close();
                byte[] bytes = baos.toByteArray();
                clazz = defineClass(name, bytes, 0, bytes.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clazz;
    }

    private String getClassFile(String name) {
        File directory = new File(path);
        String absolutePath = directory.getAbsolutePath();
        return absolutePath + "/" + name.replace('.', '/') + ".class";
    }
}
