package com.gjh.learn.jvm.memory;

import com.gjh.learn.jvm.utils.Print;
import com.gjh.learn.jvm.utils.RuntimeUtils;

import java.util.ArrayList;

/**
 * created on 2021/3/27
 *
 * @author kevinlights
 */
public class SimpleHeapOOM {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 1024; i++) {
            Print.GREEN("i = {}", i);
            RuntimeUtils.printMem();
            list.add(new byte[1024 * 1024]);
        }
    }
}
