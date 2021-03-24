package com.gjh.learn.jvm.gc;

import com.gjh.learn.jvm.utils.Print;

import java.lang.ref.SoftReference;

/**
 * created on 2021/3/21
 *
 * @author kevinlights
 */
public class SoftRef {
    public static class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        User u = new User(1, "kevin");
        SoftReference<User> userSoftRef = new SoftReference<User>(u);
        u = null;

        Print.GREEN(userSoftRef.get());
        System.gc();
        Print.BLUE("after gc");
        Print.GREEN(userSoftRef.get());

        byte[] b = new byte[1024 * 910 * 7];
        System.gc();
        Print.BLUE("after alloc big object and gc");
        Print.GREEN(userSoftRef.get());
    }
}
