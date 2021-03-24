package com.gjh.learn.jvm.gc;

import com.gjh.learn.jvm.utils.LogUtil;

/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class SimpleHeap {
    private int id;

    public SimpleHeap(int id) {
        this.id = id;
    }
    private void show() {
        LogUtil.info("My ID is: {}", id);
    }
    public static void main(String[] args) {
        SimpleHeap s1 = new SimpleHeap(1);
        SimpleHeap s2 = new SimpleHeap(2);
        s1.show();
        s2.show();
    }

}
