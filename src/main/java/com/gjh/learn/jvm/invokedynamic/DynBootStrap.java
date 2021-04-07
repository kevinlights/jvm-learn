package com.gjh.learn.jvm.invokedynamic;

import java.lang.invoke.*;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class DynBootStrap {
    public static CallSite bootstrap(MethodHandles.Lookup lookup, String name, MethodType type, Object value) throws NoSuchMethodException, IllegalAccessException {
        System.out.println("bootstrap called, name = " + name);
        MethodHandle mh = lookup.findVirtual(value.getClass(), name, type).bindTo(value);
        return new ConstantCallSite(mh);
    }
}
