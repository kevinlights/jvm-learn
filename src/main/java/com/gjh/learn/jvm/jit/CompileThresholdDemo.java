package com.gjh.learn.jvm.jit;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class CompileThresholdDemo {
    public static void met() {
        int a = 0, b = 0;
        b = a + b;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            met();
        }
        Thread.sleep(1000);
    }
}
