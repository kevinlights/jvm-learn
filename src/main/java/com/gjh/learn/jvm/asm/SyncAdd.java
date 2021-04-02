package com.gjh.learn.jvm.asm;

/**
 * created on 2021/4/2
 *
 * @author kevinlights
 */
public class SyncAdd {
    private int i = 0;

    public synchronized void add1() {
        i++;
    }

    public void add2() {
        synchronized (this) {
            i++;
        }
    }
}
