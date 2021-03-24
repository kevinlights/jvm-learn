package com.gjh.learn.jvm.gc;

import com.gjh.learn.jvm.utils.LogUtil;

/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class StackDeep {
    private static int count = 0;

    public static void recursion() {
        count++;
        recursion();
    }

    public static void recursion(long a, long b, long c) {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count++;
        recursion(a, b, c);
    }


    public static void main(String[] args) {
        try {
            // recursion();
            recursion(0L, 0L, 0L);
        } catch (Throwable e) {
            LogUtil.error("deep of calling = {}", count);
        }
    }
}
