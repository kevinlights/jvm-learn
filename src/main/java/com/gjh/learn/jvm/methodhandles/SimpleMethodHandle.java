package com.gjh.learn.jvm.methodhandles;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class SimpleMethodHandle {
    static class MyPrintln {
        protected void println(String s) {
            System.out.println("MyPrintln: " + s);
        }
    }

    public static void main(String[] args) throws Throwable {
        while (true) {
            Object obj = (System.currentTimeMillis() & 1L) == 0L ? System.out : new MyPrintln();
            System.out.println(obj.getClass().toString());
            getPrintlnMethodHandler(obj).invokeExact("guang");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static MethodHandle getPrintlnMethodHandler(Object receiver) throws NoSuchMethodException, IllegalAccessException {
        MethodType mt = MethodType.methodType(void.class, String.class);
        return lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
    }
}
