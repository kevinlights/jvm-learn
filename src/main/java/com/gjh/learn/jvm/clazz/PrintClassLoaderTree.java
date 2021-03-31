package com.gjh.learn.jvm.clazz;

import com.gjh.learn.jvm.utils.Print;

/**
 * created on 2021/3/31
 *
 * @author kevinlights
 */
public class PrintClassLoaderTree {
    public static void main(String[] args) {
        ClassLoader cl = PrintClassLoaderTree.class.getClassLoader();
        while (cl != null) {
            Print.GREEN(cl);
            cl = cl.getParent();
        }
        Print.BLACK("==============================");
        Print.GREEN(String.class.getClassLoader());
    }
}
