package com.gjh.learn.jvm.clazz;

/**
 * created on 2021/3/31
 *
 * @author kevinlights
 */
public class StaticDeadLockMain extends Thread {
    private char flag;
    public StaticDeadLockMain(char flag) {
        this.flag = flag;
        this.setName("Thread-" + flag);
    }

    @Override
    public void run() {
        try {
            Class.forName("com.gjh.learn.jvm.clazz.Static" + flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " over");
    }

    public static void main(String[] args) {
        StaticDeadLockMain loadA = new StaticDeadLockMain('A');
        loadA.start();
        StaticDeadLockMain loadB = new StaticDeadLockMain('B');
        loadB.start();
    }
}

class StaticA {
    static {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.gjh.learn.jvm.clazz.StaticB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("StaticA init successful");
    }
}

class StaticB {
    static {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.gjh.learn.jvm.clazz.StaticA");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("StaticB init successful");
    }
}
