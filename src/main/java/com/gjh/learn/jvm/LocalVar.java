package com.gjh.learn.jvm;

/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class LocalVar {

    public static void localvar1() {
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    /**
     * 这种情况下，局部变量 a 失效，变量 b 会复用 a 的槽位 (index)，因此在 jclasslib 中可以看到它们共用同一个 index
     */
    public static void localvar2() {
        {
            int a = 0;
            System.out.println(a);
        }
        int b = 0;
    }

    public static void main(String[] args) {
        localvar1();
        localvar2();
    }
}
