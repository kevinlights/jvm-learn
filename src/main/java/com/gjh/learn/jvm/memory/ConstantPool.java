package com.gjh.learn.jvm.memory;

import com.gjh.learn.jvm.utils.Print;

/**
 * created on 2021/3/28
 *
 * @author kevinlights
 */
public class ConstantPool {
    public static void main(String[] args) {
        String abc = "abc";
        Print.GREEN(System.identityHashCode((abc + Integer.toString(0))));
        Print.GREEN(System.identityHashCode((abc + Integer.toString(0)).intern()));
        // System.gc();
        // 如果进行一次 gc ，字面量相同的字符串重新被加入常量池，引用位置不同
        Print.GREEN(System.identityHashCode((abc + Integer.toString(0)).intern()));
    }
}
