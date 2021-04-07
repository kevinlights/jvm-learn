package com.gjh.learn.jvm.jit;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class WriterMain {
    public static void main(String[] args) throws InterruptedException {
        long b = System.currentTimeMillis();
        WriterService ws = new WriterService();
        for (int i = 0; i < 20000000; i++) {
            // 815 1118 %  b  4       com.gjh.learn.jvm.jit.WriterMain::main @ 15 (73 bytes)
            ws.service();
        }
        System.out.println("WriterMain spend: " + (System.currentTimeMillis() - b));
        ws = null;
        System.gc();
        Thread.sleep(5000);
    }
}

class WriterService {
    public void service() {
        DBWriter writer = new DBWriter();
        writer.write();
    }
}

class DBWriter {
    public void write() {
        "DBWriter".toCharArray();
    }
}
