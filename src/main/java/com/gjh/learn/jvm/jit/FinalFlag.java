package com.gjh.learn.jvm.jit;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class FinalFlag {
    public static final boolean flag = true;

    public void checkflag() {
        if (flag) {
            System.out.println("flag is true");
        } else {
            System.out.println("flag is false");
        }
    }
}
