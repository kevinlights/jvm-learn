package com.gjh.learn.jvm.utils;

import org.slf4j.helpers.MessageFormatter;

/**
 * created on 2021/3/20
 *
 * @author kevinlights
 */
public class Print {
    public static final String BLACK = "\033[30m%s\033[0m";
    public static final String RED = "\033[31m%s\033[0m";
    public static final String GREEN = "\033[32m%s\033[0m";
    public static final String YELLOW = "\033[33m%s\033[0m";
    public static final String BLUE = "\033[34m%s\033[0m";
    public static final String PURPLE = "\033[35m%s\033[0m";
    public static final String LIGHT_BLUE = "\033[36m%s\033[0m";
    public static final String GREY = "\033[37m%s\033[0m";

    public static final String BLACK_BG = "\033[40m%s\033[0m";
    public static final String RED_BG = "\033[41m%s\033[0m";
    public static final String GREEN_BG = "\033[42m%s\033[0m";
    public static final String YELLOW_BG = "\033[43m%s\033[0m";
    public static final String BLUE_BG = "\033[44m%s\033[0m";
    public static final String PURPLE_BG = "\033[45m%s\033[0m";
    public static final String LIGHT_BLUE_BG = "\033[46m%s\033[0m";
    public static final String GREY_BG = "\033[47m%s\033[0m";

    public static final String BLACK_HIGH = "\033[90m%s\033[0m";
    public static final String RED_HIGH = "\033[91m%s\033[0m";
    public static final String GREEN_HIGH = "\033[92m%s\033[0m";
    public static final String YELLOW_HIGH = "\033[93m%s\033[0m";
    public static final String BLUE_HIGH = "\033[94m%s\033[0m";
    public static final String PURPLE_HIGH = "\033[95m%s\033[0m";
    public static final String LIGHT_BLUE_HIGH = "\033[96m%s\033[0m";
    public static final String GREY_HIGH = "\033[97m%s\033[0m";

    public static void BLACK(String msg, Object... args) {
        println(BLACK, msg, args);
    }

    public static void RED(String msg, Object... args) {
        println(RED, msg, args);
    }

    public static void GREEN(String msg, Object... args) {
        println(GREEN, msg, args);
    }

    public static void YELLOW(String msg, Object... args) {
        println(YELLOW, msg, args);
    }

    public static void BLUE(String msg, Object... args) {
        println(BLUE, msg, args);
    }

    public static void PURPLE(String msg, Object... args) {
        println(PURPLE, msg, args);
    }

    public static void LIGHT_BLUE(String msg, Object... args) {
        println(LIGHT_BLUE, msg, args);
    }

    public static void GREY(String msg, Object... args) {
        println(GREY, msg, args);
    }

    public static void BLACK_BG(String msg, Object... args) {
        println(BLACK_BG, msg, args);
    }

    public static void RED_BG(String msg, Object... args) {
        println(RED_BG, msg, args);
    }

    public static void GREEN_BG(String msg, Object... args) {
        println(GREEN_BG, msg, args);
    }

    public static void YELLOW_BG(String msg, Object... args) {
        println(YELLOW_BG, msg, args);
    }

    public static void BLUE_BG(String msg, Object... args) {
        println(BLUE_BG, msg, args);
    }

    public static void PURPLE_BG(String msg, Object... args) {
        println(PURPLE_BG, msg, args);
    }

    public static void LIGHT_BLUE_BG(String msg, Object... args) {
        println(LIGHT_BLUE_BG, msg, args);
    }

    public static void GREY_BG(String msg, Object... args) {
        println(GREY_BG, msg, args);
    }

    public static void BLACK_HIGH(String msg, Object... args) {
        println(BLACK_HIGH, msg, args);
    }

    public static void RED_HIGH(String msg, Object... args) {
        println(RED_HIGH, msg, args);
    }

    public static void GREEN_HIGH(String msg, Object... args) {
        println(GREEN_HIGH, msg, args);
    }

    public static void YELLOW_HIGH(String msg, Object... args) {
        println(YELLOW_HIGH, msg, args);
    }

    public static void BLUE_HIGH(String msg, Object... args) {
        println(BLUE_HIGH, msg, args);
    }

    public static void PURPLE_HIGH(String msg, Object... args) {
        println(PURPLE_HIGH, msg, args);
    }

    public static void LIGHT_BLUE_HIGH(String msg, Object... args) {
        println(LIGHT_BLUE_HIGH, msg, args);
    }

    public static void GREY_HIGH(String msg, Object... args) {
        println(GREY_HIGH, msg, args);
    }

    private static void println(String color, String msg, Object... args) {
        System.out.println(String.format(color, MessageFormatter.arrayFormat(msg, args).getMessage()));
    }


}
