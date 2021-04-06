package com.gjh.learn.jvm.methodhandles;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class SimpleStaticMethodHandle {
    public static void main(String[] args) throws Throwable {
        SimpleStaticMethodHandle obj = new SimpleStaticMethodHandle();
        System.out.println(obj.callSin());
    }

    public double callSin() throws Throwable {
        MethodHandle mh = MethodHandles.lookup().findStatic(Math.class, "sin", MethodType.methodType(double.class, double.class));
        return (double) mh.invokeExact(Math.PI / 2);
        // return (double) mh.bindTo(this).invokeExact(Math.PI / 2); // java.lang.IllegalArgumentException: no leading reference parameter
    }
}
