package com.gjh.learn.jvm.thread;

/**
 * created on 2021/3/25
 *
 * @author kevinlights
 */
public class HoldCPUMain {
    public static class HoldCPUTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                double a = Math.random() * Math.random() * Math.random();// 占用 CPU
            }
        }
    }

    public static class LazyTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);// 空闲线程
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new HoldCPUTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
    }
}
