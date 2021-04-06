package com.gjh.learn.jvm.methodhandles;

import java.lang.invoke.*;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class CallSiteDemo {
    public static void constantCallSite() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class, int.class, int.class);
        MethodHandle mh = lookup.findVirtual(String.class, "substring", type);
        ConstantCallSite callSite = new ConstantCallSite(mh);
        MethodHandle invoker = callSite.dynamicInvoker();
        Object result = invoker.invoke("1234567890", 2, 4);
        System.out.println("constantCallSite -> result: " + result);
    }

    public static void mutableCallSite() throws Throwable {
        MethodType type = MethodType.methodType(double.class, double.class);
        MutableCallSite callSite = new MutableCallSite(type);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle sin = lookup.findStatic(Math.class, "sin", type);
        MethodHandle cos = lookup.findStatic(Math.class, "cos", type);
        callSite.setTarget(sin);
        MethodHandle invoker = callSite.dynamicInvoker();
        double result = (double) invoker.invoke(Math.PI / 2);
        System.out.println("mutableCallSite -> sin(90) = " + result);
        callSite.setTarget(cos);
        result = (double) invoker.invoke(Math.PI / 2);
        System.out.println("mutableCallSite -> cos(90) = " + result);
    }

    public static void main(String[] args) throws Throwable {
        constantCallSite();
        mutableCallSite();
    }
}
