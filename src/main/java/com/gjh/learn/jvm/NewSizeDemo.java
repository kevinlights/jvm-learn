package com.gjh.learn.jvm;

/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class NewSizeDemo {
    public static void main(String[] args) {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
    }
}
