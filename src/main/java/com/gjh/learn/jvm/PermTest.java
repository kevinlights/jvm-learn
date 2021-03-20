package com.gjh.learn.jvm;

import com.gjh.learn.jvm.utils.CglibBean;

import java.util.HashMap;

import static com.gjh.learn.jvm.utils.Constant.BOLD_GREEN_UNDERLINE_PREFIX;
import static com.gjh.learn.jvm.utils.Constant.SUFFIX;

/**
 * created on 2021/3/20
 * 使用 CGLIB 生成大量的动态类
 * @author kevinlights
 */
public class PermTest {

    public static void main(String[] args) {
        int i = 0;
        try {
            for (i = 0; i < 100000; i++) {
                CglibBean bean = new CglibBean("com.gjh.learn.jvm" + i, new HashMap());
            }
        } catch (Exception e) {
            System.out.println(BOLD_GREEN_UNDERLINE_PREFIX + "create count: " + i + SUFFIX);
        }
    }
}
