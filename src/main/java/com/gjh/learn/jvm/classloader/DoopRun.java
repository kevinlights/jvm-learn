package com.gjh.learn.jvm.classloader;

import java.lang.reflect.Method;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class DoopRun {
    public static void main(String[] args) {
        while (true) {
            try {
                MyClassLoader loader = new MyClassLoader("E:\\Study\\Java\\jvm\\jvm-learn\\clz");
                Class<?> cls = loader.loadClass("com.gjh.learn.jvm.classloader.DemoA");
                Object demo = cls.newInstance();
                Method hot = demo.getClass().getMethod("hot", new Class[]{});
                hot.invoke(demo, new Object[]{});
                Thread.sleep(10000);
            } catch (Exception e) {
                System.out.println("not find class");
                e.printStackTrace();
            }
        }
    }
}
