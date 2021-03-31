package com.gjh.learn.jvm.clazz;

import com.gjh.learn.jvm.utils.Print;

/**
 * created on 2021/3/31
 *
 * @author kevinlights
 */
public class FindClassOrder {
    public static void main(String[] args) {
        HelloLoader loader = new HelloLoader();
        loader.print();
        Print.GREEN(HelloLoader.class.getClassLoader());
    }
}
