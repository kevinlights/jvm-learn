package com.gjh.learn.jvm.lock;

import com.gjh.learn.jvm.utils.Print;

import java.util.List;
import java.util.Vector;

/**
 * created on 2021/3/28
 *
 * 偏向锁测试
 * @author kevinlights
 */
public class Biased {
    public static List<Integer> numberList = new Vector<>();

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        int count = 0;
        int startNum = 0;
        while (count < 10000000) {
            numberList.add(startNum);
            startNum += 2;
            count++;
        }
        Print.GREEN(System.currentTimeMillis() - b);
    }
}
