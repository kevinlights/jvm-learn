package com.gjh.learn.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * created on 2021/3/28
 *
 * @author kevinlights
 */
public class StringInternOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
