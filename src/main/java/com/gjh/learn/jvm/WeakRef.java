package com.gjh.learn.jvm;

import com.gjh.learn.jvm.utils.Print;

import java.lang.ref.WeakReference;

/**
 * created on 2021/3/21
 *
 * @author kevinlights
 */
public class WeakRef {
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
        WeakReference<User> userWeakRef = new WeakReference<User>(u);
        u = null;
        Print.GREEN(userWeakRef.get());
        System.gc();
        Print.BLUE("after gc");
        Print.GREEN(userWeakRef.get());
    }
}
