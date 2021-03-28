package com.gjh.learn.jvm.lock;

import com.gjh.learn.jvm.utils.Print;

/**
 * created on 2021/3/28
 *
 * @author kevinlights
 */
public class LockEliminate {
    private static final int CIRCLE = 2000000;

    public static void main(String args[]) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            createStringBuffer("JVM", "Diagnosis");
        }
        Print.GREEN("create StringBuffer, time cost: {}ms", System.currentTimeMillis() - b);
    }

    private static String createStringBuffer(String s1, String s2) {
        // StringBuffer 变量的作用域只限于方法体内部，不可能逃逸出该方法，不可能存在竞争
        // 因此加上锁消除后，性能会有所提高
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }
}
