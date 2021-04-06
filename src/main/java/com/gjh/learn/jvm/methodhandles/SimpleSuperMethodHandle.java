package com.gjh.learn.jvm.methodhandles;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class SimpleSuperMethodHandle {
    public static void main(String[] args) throws Throwable {
        SimpleSuperMethodHandle obj = new SimpleSuperMethodHandle();
        System.out.println(obj.callToString());
    }

    @Override
    public String toString() {
        return "I am SimpleSuperMethodHandle";
    }

    public String callToString() throws Throwable {
        return (String) MethodHandles.lookup().findSpecial(Object.class, "toString", MethodType.methodType(String.class), this.getClass()).bindTo(this).invokeExact();
    }
}
