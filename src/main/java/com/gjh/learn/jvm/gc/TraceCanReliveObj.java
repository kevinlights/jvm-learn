package com.gjh.learn.jvm.gc;

import com.gjh.learn.jvm.utils.Print;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * created on 2021/3/21
 *
 * @author kevinlights
 */
public class TraceCanReliveObj {
    public static TraceCanReliveObj obj;

    static ReferenceQueue<TraceCanReliveObj> phantomQueue = null;

    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                if (phantomQueue != null) {
                    PhantomReference<TraceCanReliveObj> objt = null;
                    try {
                        objt = (PhantomReference<TraceCanReliveObj>) phantomQueue.remove();
                    } catch (InterruptedException e) {
                        Print.RED(e.toString());
                    }
                    if (objt != null) {
                        Print.PURPLE("TraceCanReliveObj is deleted by GC");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Print.PURPLE("CanReliveObj finalize called");
        obj = this;
    }

    @Override
    public String toString() {
        return "I am CanReliveObj";
    }

    /**
     * first gc
     * CanReliveObj finalize called     对象复活
     * obj is available
     * second gc                        finalize 只会调用一次，对象无法再复活了
     * TraceCanReliveObj is deleted by GC   引用队列捕获到对象被回收
     * obj is null
     *
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();

        phantomQueue = new ReferenceQueue<TraceCanReliveObj>();
        obj = new TraceCanReliveObj();
        PhantomReference<TraceCanReliveObj> phantomRef = new PhantomReference<TraceCanReliveObj>(obj, phantomQueue);

        obj = null;
        Print.BLUE("first gc");
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            Print.GREEN("obj is null");
        } else {
            Print.GREEN("obj is available");
        }
        Print.BLUE("second gc");
        obj = null;
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            Print.GREEN("obj is null");
        } else {
            Print.GREEN("ob is available");
        }
    }
}
