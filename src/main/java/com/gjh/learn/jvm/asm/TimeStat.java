package com.gjh.learn.jvm.asm;

/**
 * created on 2021/4/6
 *
 * @author kevinlights
 */
public class TimeStat {
    static ThreadLocal<Long> t = new ThreadLocal<>();

    public static void start() {
        t.set(System.currentTimeMillis());
    }

    public static void end() {
        long time = System.currentTimeMillis() - t.get();
        System.out.println(Thread.currentThread().getStackTrace()[2] + " spend:" + time);
    }
}
