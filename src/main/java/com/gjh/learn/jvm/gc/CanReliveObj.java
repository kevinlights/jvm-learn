package com.gjh.learn.jvm.gc;

import com.gjh.learn.jvm.utils.Print;

/**
 * created on 2021/3/21
 *
 * @author kevinlights
 */
public class CanReliveObj {
    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Print.PURPLE("CanReliveObj finalize called");
        obj = this;
    }

    public static void main(String[] args) throws InterruptedException {
        obj = new CanReliveObj();

        doGc();
        Print.BLUE("second gc");
        doGc();
    }

    private static void doGc() throws InterruptedException {
        obj = null;
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            Print.RED("obj is null");
        } else {
            Print.GREEN("obj is available");
        }
    }
}
