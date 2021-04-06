package com.gjh.learn.jvm.asm;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class SlowTask {
    public void operation() {
        System.out.println("operation....");
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
