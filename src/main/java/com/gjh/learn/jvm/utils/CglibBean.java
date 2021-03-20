package com.gjh.learn.jvm.utils;

import net.sf.cglib.beans.BeanGenerator;

/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class CglibBean {

    public CglibBean(String name, Object obj) {
        BeanGenerator generator = new BeanGenerator();
        generator.addProperty(name, obj.getClass());
        generator.create();
    }
}
