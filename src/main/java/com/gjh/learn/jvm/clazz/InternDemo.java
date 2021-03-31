package com.gjh.learn.jvm.clazz;

import com.gjh.learn.jvm.utils.Print;

/**
 * created on 2021/3/31
 *
 * @author kevinlights
 */
public class InternDemo {
    public static void main(String[] args) {
        String a = Integer.toString(1) + 2 + 3;
        String b = "123";
        Print.GREEN(a.equals(b));
        Print.GREEN(a == b);
        Print.GREEN(a.intern() == b);
    }
}
