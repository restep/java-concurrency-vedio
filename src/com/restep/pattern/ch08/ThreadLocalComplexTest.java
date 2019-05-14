package com.restep.pattern.ch08;

import java.util.Random;

/**
 * @author restep
 * @date 2019/5/14
 */
public class ThreadLocalComplexTest {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                THREAD_LOCAL.set("t1");

                try {
                    Thread.sleep(RANDOM.nextInt(1_000));

                    System.out.println(Thread.currentThread().getName() + ": " + THREAD_LOCAL.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                THREAD_LOCAL.set("t2");

                try {
                    Thread.sleep(RANDOM.nextInt(1_000));

                    System.out.println(Thread.currentThread().getName() + ": " + THREAD_LOCAL.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ": " + THREAD_LOCAL.get());
    }
}
