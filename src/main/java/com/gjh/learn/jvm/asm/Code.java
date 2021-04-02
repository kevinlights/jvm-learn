package com.gjh.learn.jvm.asm;

/**
 * created on 2021/4/2
 *
 * @author kevinlights
 */
public class Code {
    public void print11(int j) {
        int i = 123;
        i = i + 10;

        int k = -i;
    }

    public void testNewObj() {
        int[] intarr = new int[10];
        Object[] objarr = new Object[10];
        int[][] mintarr = new int[10][10];
    }

    public void testfield() {
        System.out.println("hello");
    }

    public String checkcase(Object obj) {
        if (obj instanceof String)
            return (String) obj;
        else
            return null;
    }

    public void testcondition() {
        float f1 = 9;
        float f2 = 10;
        System.out.println(f1 > f2);
    }

    public void testcmpl() {
        short s1 = 9;
        byte b2 = 10;
        System.out.println(s1 > b2);
    }

    public void testcmpobj() {
        Object o1 = new Object();
        Object o2 = new Object();

        System.out.println(o1 == o2);
        System.out.println(o1 != o2);
    }

    public void switch1(int i) {
        switch (i) {
            case 1: break;
            case 2: break;
            case 3: break;
        }
    }

    public void switch2(int i) {
        switch (i) {
            case 100: break;
            case 200: break;
            case 300: break;
        }
    }

    public void switch3(String str) {
        switch (str) {
            case "aaa": break;
            case "bbb": break;
            case "ccc": break;
        }
    }
}
