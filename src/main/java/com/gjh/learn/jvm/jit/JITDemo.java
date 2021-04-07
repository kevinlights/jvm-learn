package com.gjh.learn.jvm.jit;

/**
 * created on 2021/4/7
 *
 * @author kevinlights
 */
public class JITDemo {
    public void method() {
        for (int i = 0; i < 60 * 60 * 24 * 1000; i++) {
            System.out.println(i);
        }
    }

    /**
     *    L0
     *     LINENUMBER 16 L0
     *     LDC "select * from test"
     *     ASTORE 0
     *    L1
     *     LINENUMBER 17 L1
     *     LDC "select * from test"
     *     ASTORE 1
     *    L2
     *     LINENUMBER 18 L2
     *     LDC "select * "
     *     LDC "from test"
     *     INVOKEVIRTUAL java/lang/String.concat (Ljava/lang/String;)Ljava/lang/String;
     *     ASTORE 2
     */
    public static void createString() {
        String info1 = "select * from test";
        String info2 = "select * " + "from test";
        String info3 = "select * ".concat("from test");
        System.out.println(info1 == info2); // true
        System.out.println(info1 == info3); // false
        System.out.println(info2 == info3); // false
        System.out.println(info2 == info3.intern()); // true
    }

    public static void addString(String str1, String str2) {
        String str3 = str1 + str2;
    }

    public static void addString2(String ...str1) {
        String str2 = "";
        for (String str : str1) {
            str2 += str;
        }
    }

    public static void main(String[] args) {
        createString();
    }
}
