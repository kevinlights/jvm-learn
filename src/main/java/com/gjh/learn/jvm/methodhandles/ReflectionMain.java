package com.gjh.learn.jvm.methodhandles;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class ReflectionMain {
    public static final int COUNT = 10000000;
    int i = 0;

    public void method() {
        i++;
    }

    public static void callByHandler() throws Throwable {
        ReflectionMain inst = new ReflectionMain();
        MethodType type = MethodType.methodType(void.class);
        MethodHandle mh = MethodHandles.lookup().findVirtual(inst.getClass(), "method", type).bindTo(inst);
        long b = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            mh.invokeExact();
        }
        System.out.println("callByHandler -> time cost: " + (System.currentTimeMillis() - b));
    }

    public static void callByReflection() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ReflectionMain inst = new ReflectionMain();
        Method m = inst.getClass().getMethod("method");
        long b = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            m.invoke(inst);
        }
        System.out.println("callByReflection -> time cost: " + (System.currentTimeMillis() - b));
    }

    public static void main(String[] args) throws Throwable {
        callByHandler();
        callByHandler();
        callByReflection();
        callByReflection();
    }
}
