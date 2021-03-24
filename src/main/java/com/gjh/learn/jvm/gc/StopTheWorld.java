package com.gjh.learn.jvm.gc;

import com.gjh.learn.jvm.utils.Print;

import java.util.HashMap;

/**
 * created on 2021/3/21
 *
 * @author kevinlights
 */
public class StopTheWorld {
    public static class MyThread extends Thread {
        HashMap map = new HashMap();

        @Override
        public void run() {
            try {
                while (true) {
                    if (map.size() * 512 / 1024 / 1025 >= 900) {
                        map.clear();
                        Print.BLUE("clean map");
                    }
                    byte[] b;
                    for (int i = 0; i < 100; i++) {
                        b = new byte[512];
                        map.put(System.nanoTime(), b);
                    }
                    Thread.sleep(1);
                }
            } catch (Exception e) {
                Print.RED(e.toString());
            }
        }
    }

    public static class PrintThread extends Thread {
        public static final long start = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true) {
                    long t = System.currentTimeMillis() - start;
                    Print.GREEN(t / 1000 + "." + t % 1000);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                Print.RED(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        PrintThread p = new PrintThread();
        t.start();
        p.start();
    }
}
