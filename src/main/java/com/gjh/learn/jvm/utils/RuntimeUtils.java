package com.gjh.learn.jvm.utils;

/**
 * created on 2021/3/27
 *
 * @author kevinlights
 */
public class RuntimeUtils {
    public static void printMem() {
        Print.GREEN("Memory: free={}m\ttotal={}m\tmax={}m", Runtime.getRuntime().freeMemory() / 1024 / 1024,
                Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().maxMemory() / 1024 / 1024);
    }
}
