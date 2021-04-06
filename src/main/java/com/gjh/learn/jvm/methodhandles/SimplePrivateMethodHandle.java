package com.gjh.learn.jvm.methodhandles;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class SimplePrivateMethodHandle {
    public static void main(String[] args) throws Throwable {
        SimplePrivateMethodHandle obj = new SimplePrivateMethodHandle();
        obj.callPrintLine();
    }

    private void printLine() {
        System.out.println("call private method");
    }

    public void callPrintLine() throws Throwable {
        MethodHandles.lookup().findSpecial(this.getClass(), "printLine", MethodType.methodType(void.class), this.getClass()).bindTo(this).invokeExact();
        // MethodHandles.lookup().findSpecial(this.getClass(), "printLine", MethodType.methodType(void.class), this.getClass()).invokeExact(); // java.lang.invoke.WrongMethodTypeException: expected (SimplePrivateMethodHandle)void but found ()void
    }
}
