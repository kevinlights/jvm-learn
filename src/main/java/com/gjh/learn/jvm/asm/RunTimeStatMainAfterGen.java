package com.gjh.learn.jvm.asm;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class RunTimeStatMainAfterGen {
    public static void main(String[] args) {
        SlowTask slowTask = new SlowTask();
        slowTask.operation();
    }
}
