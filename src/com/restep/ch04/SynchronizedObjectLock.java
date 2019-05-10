package com.restep.ch04;

/**
 * @author restep
 * @date 2019/5/10
 */
public class SynchronizedObjectLock {
    public static void main(String[] args) {
        ObjectLock objectLock = new ObjectLock();

        new Thread("t1") {
            @Override
            public void run() {
                objectLock.m1();
            }
        }.start();
        new Thread("t2") {
            @Override
            public void run() {
                objectLock.m2();
            }
        }.start();
    }
}

/**
 * m1()和m2()几乎同时抢到锁 TODO
 * m1()和m2()拿到的锁是不一样的
 * m1()是this锁 m2()是lock锁
 */
class ObjectLock {
    private final Object LOCK = new Object();

    public synchronized void m1() {
        try {
            System.out.println("m1: " + Thread.currentThread().getName());
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2() {
        synchronized (LOCK) {
            try {
                System.out.println("m2: " + Thread.currentThread().getName());
                Thread.sleep(2_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
