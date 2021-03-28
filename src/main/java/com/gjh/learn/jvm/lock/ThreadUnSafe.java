package com.gjh.learn.jvm.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * created on 2021/3/28
 *
 * @author kevinlights
 */
public class ThreadUnSafe {
    // Vector 使用　synchronized 进行同步
    // public static List<Integer> numberList = new ArrayList<>();
    public static List<Integer> numberList = new Vector<>();

    public static class AddToList implements Runnable {
        int startNum = 0;

        public AddToList(int startNum) {
            startNum= startNum;
        }

        @Override
        public void run() {
            int count = 0;
            while (count < 1000000) {
                numberList.add(startNum);
                startNum += 2;
                count++;
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new AddToList(0));
        Thread t2 = new Thread(new AddToList(1));
        t1.start();
        t2.start();
    }
}
