package com.gjh.learn.jvm.jit;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class InLineMain {
    static int i = 0;

    public static void inc() {
        i++;
    }

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        // 会内联优化： -XX:+Inline -XX:-Inline -XX:FreqInlineSize
        /**
         * for (int j = 0; j < 100000000; j++) {
         *             i++
         *         }
         */
        for (int j = 0; j < 100000000; j++) {
            inc();
        }
        System.out.println("InLineMain spend: " + (System.currentTimeMillis() - b));
    }
}
