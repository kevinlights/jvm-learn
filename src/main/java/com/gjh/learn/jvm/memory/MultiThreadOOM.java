package com.gjh.learn.jvm.memory;

import com.gjh.learn.jvm.utils.Print;
import com.gjh.learn.jvm.utils.RuntimeUtils;

/**
 * created on 2021/3/27
 *
 * @author kevinlights
 */
public class MultiThreadOOM {
    public static class SleepThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(10000000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5500; i++) {
            new Thread(new SleepThread(), "sleep-thread-" + i).start();
            Print.GREEN("i = {}", i);
            RuntimeUtils.printMem();
        }
    }
}
