package com.gjh.learn.jvm;

import com.gjh.learn.jvm.utils.Print;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * created on 2021/3/21
 *
 * @author kevinlights
 */
public class WeakRefQueue {
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

    /**
     * 自定义软引用类，记录 User.uid
     */
    public static class UserWeakReference extends WeakReference<User> {
        int uid;

        public UserWeakReference(User referent, ReferenceQueue<? super User> q) {
            super(referent, q);
            uid = referent.id;
        }
    }

    /**
     * 当给定的对象实例被回收时，会被加入此引用队列，通过访问该队列可以跟踪对象的回收情况
     */
    static ReferenceQueue<User> weakQueue = null;

    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                if (weakQueue != null) {
                    UserWeakReference obj = null;
                    try {
                        obj = (UserWeakReference) weakQueue.remove();
                    } catch (InterruptedException e) {
                        Print.RED(e.toString());
                    }
                    if (obj != null) {
                        Print.PURPLE("user id [{}] is deleted", obj.uid);
                    }
                }
            }
        }
    }

    /**
     * User{id=1, name='kevin'}     第一次从软引用获得对象
     * after gc
     * User{id=1, name='kevin'}     gc 后，软引用对象没有回收
     * try to create byte array and GC  创建大数组，耗尽内存
     * user id [1] is deleted   引用队列探测到对象被删除
     * after gc
     * null                     对象已被回收，无法再通过软引用获取对象
     *
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();
        User u = new User(1, "kevin");
        weakQueue = new ReferenceQueue<User>();
        UserWeakReference userWeakRef = new UserWeakReference(u, weakQueue);
        u = null;
        Print.GREEN(userWeakRef.get());
        System.gc();
        Print.BLUE("after gc");
        Print.GREEN(userWeakRef.get());
        Thread.sleep(1000);
    }
}
