package com.gjh.learn.jvm.performance;

/**
 * created on 2021/3/27
 *
 * @author kevinlights
 */
public class HProfTest {
    public void slowMethod() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void slowerMethod() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fastMethod() {
        try {
            Thread.yield();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HProfTest test = new HProfTest();
        test.fastMethod();
        test.slowMethod();
        test.slowerMethod();
    }
}
