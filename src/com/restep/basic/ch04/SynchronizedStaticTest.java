package com.restep.basic.ch04;

/**
 * m1()和m2()使用同一个锁
 * 静态代码块使用class锁
 *
 * @author restep
 * @date 2019/5/10
 */
public class SynchronizedStaticTest {
    public static void main(String[] args) {
        new Thread("t1") {
            @Override
            public void run() {
                SynchronizedStatic.m1();
            }
        }.start();

        new Thread("t2") {
            @Override
            public void run() {
                SynchronizedStatic.m2();
            }
        }.start();

        new Thread("t3") {
            @Override
            public void run() {
                SynchronizedStatic.m3();
            }
        }.start();
    }
}

class SynchronizedStatic {
    static {
        synchronized (SynchronizedStatic.class) {
            System.out.println("static: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1() {
        System.out.println("m1: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2() {
        System.out.println("m2: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3() {
        System.out.println("m3: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
