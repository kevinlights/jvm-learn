package com.gjh.learn.jvm;

import com.gjh.learn.jvm.utils.Print;

import java.nio.ByteBuffer;

/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class AllocDirectBuffer {
    public void directAllocate() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            ByteBuffer b = ByteBuffer.allocateDirect(1000);
        }
        Print.GREEN("direct allocate: {}", System.currentTimeMillis() - start);
    }

    public void bufferAllocate() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            ByteBuffer b = ByteBuffer.allocate(1000);
        }
        Print.GREEN("buffer allocate: {}", System.currentTimeMillis() - start);
    }

    public static void main(String[] args) {
        AllocDirectBuffer alloc = new AllocDirectBuffer();
        alloc.bufferAllocate();
        alloc.directAllocate();

        alloc.bufferAllocate();
        alloc.directAllocate();
    }
}
