package com.gjh.learn.jvm.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * created on 2021/3/25
 *
 * @author kevinlights
 */
public class GCTestMain {
    public static class HoldIOTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    FileOutputStream fos = new FileOutputStream(new File("tempfile"));
                    for (int i = 0; i < 10000; i++) {
                        fos.write(i);
                    }
                    fos.close();
                    FileInputStream fis = new FileInputStream(new File("tempfile"));
                    while (fis.read() != -1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class LazyTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new HoldIOTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
    }
}
