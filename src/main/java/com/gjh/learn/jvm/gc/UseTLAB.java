package com.gjh.learn.jvm.gc;

import com.gjh.learn.jvm.utils.Print;

/**
 * created on 2021/3/23
 *
 * @author kevinlights
 */
public class UseTLAB {
    public static void alloc() {
        byte[] b = new byte[2];
        b[0] = 1;
    }

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        Print.GREEN(System.currentTimeMillis() - b);
    }
}
