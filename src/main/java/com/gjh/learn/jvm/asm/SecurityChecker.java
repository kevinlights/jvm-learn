package com.gjh.learn.jvm.asm;

/**
 * created on 2021/4/2
 *
 * @author kevinlights
 */
public class SecurityChecker {
    public static boolean checkSecurity() {
        System.out.println("SecurityChecker.checkSecurity...");
        if ((System.currentTimeMillis() & 0x1) == 0) {
            return false;
        } else {
            return true;
        }
    }
}
