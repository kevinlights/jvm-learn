package com.gjh.learn.jvm;

/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class LocalVarGC {
    /**
     * [GC (System.gc())  10077K->6992K(251392K), 0.0048086 secs]
     * [Full GC (System.gc())  6992K->6806K(251392K), 0.0042496 secs]
     */
    public void localvarGc1() {
        byte[] a = new byte[6 * 1024 * 1024];
        System.gc();
    }

    /**
     * [GC (System.gc())  10077K->816K(251392K), 0.0010948 secs]
     * [Full GC (System.gc())  816K->635K(251392K), 0.0043472 secs]
     */
    public void localvarGc2() {
        byte[] a = new byte[6 * 1024 * 1024];
        a = null;
        System.gc();
    }

    /**
     * [GC (System.gc())  10077K->6992K(251392K), 0.0045104 secs]
     * [Full GC (System.gc())  6992K->6782K(251392K), 0.0047309 secs]
     */
    public void localvarGc3() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        System.gc();
    }

    /**
     * 变量 a 会失效，同时 c 复用了变量 a 的字，a 可以顺利回收
     * [GC (System.gc())  10077K->848K(251392K), 0.0009084 secs]
     * [Full GC (System.gc())  848K->638K(251392K), 0.0048385 secs]
     */
    public void localvarGc4() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        int c = 10;
        System.gc();
    }

    /**
     * [GC (System.gc())  10077K->6960K(251392K), 0.0044726 secs]
     * [Full GC (System.gc())  6960K->6782K(251392K), 0.0043879 secs]
     * [GC (System.gc())  8093K->6846K(251392K), 0.0002889 secs]
     * [Full GC (System.gc())  6846K->638K(251392K), 0.0028458 secs]
     */
    public void localvarGc5() {
        localvarGc1();
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGC localVarGC = new LocalVarGC();
        localVarGC.localvarGc4();
    }
}
