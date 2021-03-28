package com.gjh.learn.jvm.memory;

import com.gjh.learn.jvm.utils.CglibBean;
import com.gjh.learn.jvm.utils.Print;
import com.gjh.learn.jvm.utils.RuntimeUtils;

import java.util.HashMap;

/**
 * created on 2021/3/27
 *
 * @author kevinlights
 */
public class ClassOOM {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10000; i++) {
                Print.GREEN("i = {}", i);
                RuntimeUtils.printMem();
                CglibBean bean = new CglibBean("com.gjh.learn.jvm.bean" + i, new HashMap());
            }
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
