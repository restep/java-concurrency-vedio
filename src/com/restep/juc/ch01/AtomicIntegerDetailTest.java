package com.restep.juc.ch01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author restep
 * @date 2019/5/16
 */
public class AtomicIntegerDetailTest {
    public static void main(String[] args) {
        AtomicInteger i1 = new AtomicInteger();
        System.out.println(i1.get());

        AtomicInteger i2 = new AtomicInteger(10);
        System.out.println(i2.get());

        i2.set(23);
        System.out.println(i2.get());

        AtomicInteger i3 = new AtomicInteger(10);
        int result = i3.getAndAdd(5);
        System.out.println(result);
        System.out.println(i3.get());

        AtomicInteger i5 = new AtomicInteger(10);
        boolean flag = i5.compareAndSet(10, 29);
        System.out.println(flag);
        System.out.println(i5.get());

        System.out.println("--------------------");

        AtomicInteger i4 = new AtomicInteger();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i4.addAndGet(1));
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i4.addAndGet(1));
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
}
