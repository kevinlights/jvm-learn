package com.gjh.learn.jvm.memory;

import com.gjh.learn.jvm.utils.Print;
import com.gjh.learn.jvm.utils.RuntimeUtils;

import java.nio.ByteBuffer;

/**
 * created on 2021/3/27
 *
 * @author kevinlights
 */
public class DirectBufferOOM {
    public static void main(String[] args) {
        for (int i = 0; i < 1024; i++) {
            ByteBuffer.allocateDirect(1024*1024);
            Print.GREEN("i = {}", i);
            RuntimeUtils.printMem();
        }
    }
}
