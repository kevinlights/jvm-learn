package com.gjh.learn.jvm.gc;

import static com.gjh.learn.jvm.utils.LogUtil.*;
/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class SimpleArgs {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            info("param[{}]: {}", i, args[i]);
        }
        info("-Xmx: {}M", Runtime.getRuntime().maxMemory() / 1024 / 1024);
    }
}
