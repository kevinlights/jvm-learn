package com.gjh.learn.jvm.agent;

import com.gjh.learn.jvm.asm.SlowTask;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class RunLoopSlowTaskMain {
    public static void main(String[] args) {
        SlowTask task = new SlowTask();
        while (true) {
            task.operation();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
