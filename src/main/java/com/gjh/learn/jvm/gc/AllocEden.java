package com.gjh.learn.jvm.gc;

/**
 * created on 2021/3/23
 *
 * @author kevinlights
 */
public class AllocEden {
    public static final int _1K = 1024;

    public static void main(String[] args) {
        for (int i = 0; i < 5 * _1K; i++) {
            byte[] b = new byte[_1K];
        }
    }
}
