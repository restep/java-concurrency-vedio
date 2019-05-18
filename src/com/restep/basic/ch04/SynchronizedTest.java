package com.restep.basic.ch04;

/**
 * jconsole jstack
 * 汇编指令 D:\Repositories\java-concurrency-vedio\out\com\restep\exchanger>javap -c TicketWindowThreadSafe.class
 * @author restep
 * @date 2019/5/10
 */
public class SynchronizedTest {
    private static Object lock = new Object();

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        Thread.sleep(200_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
