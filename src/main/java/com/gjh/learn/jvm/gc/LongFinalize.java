package com.gjh.learn.jvm.gc;

import com.gjh.learn.jvm.utils.Print;

/**
 * created on 2021/3/23
 *
 * @author kevinlights
 */
public class LongFinalize {
    public static class LF {
        private byte[] content = new byte[512];

        @Override
        protected void finalize() throws Throwable {
            try {
                Print.GREEN(Thread.currentThread().getId());
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            LF f = new LF();
        }
        Print.BLUE(System.currentTimeMillis() - b);
    }
}
