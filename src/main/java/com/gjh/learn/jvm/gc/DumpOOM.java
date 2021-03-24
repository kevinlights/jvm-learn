package com.gjh.learn.jvm.gc;

import java.util.Vector;

/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class DumpOOM {
    public static void main(String[] args) {
        Vector v = new Vector();
        for (int i = 0; i < 25; i++) {
            v.add(new byte[1 * 1024 * 1024]);
        }
    }
}
