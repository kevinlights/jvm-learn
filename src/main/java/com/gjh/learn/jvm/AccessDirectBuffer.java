package com.gjh.learn.jvm;

import com.gjh.learn.jvm.utils.Print;

import java.nio.ByteBuffer;

/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class AccessDirectBuffer {
    public void directAccess() {
        long start = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocateDirect(500);
        putAndGet(b);
        Print.GREEN("direct write: {}", System.currentTimeMillis() - start);
    }

    private void putAndGet(ByteBuffer b) {
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                b.putInt(j);
            }
            b.flip();
            for (int j = 0; j < 99; j++) {
                b.getInt();
            }
            b.clear();
        }
    }

    public void bufferAccess() {
        long start = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocate(500);
        putAndGet(b);
        Print.GREEN("buffer write: {}", System.currentTimeMillis() - start);
    }

    public static void main(String[] args) {
        AccessDirectBuffer alloc = new AccessDirectBuffer();
        alloc.bufferAccess();
        alloc.directAccess();

        alloc.bufferAccess();
        alloc.directAccess();
    }
}
