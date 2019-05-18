package com.restep.juc.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * value = value + 1
 * 1. get value from main memory to local memory
 * 2. add 1
 * 3. assign the value
 *
 * @author restep
 * @date 2019/5/16
 */
public class AtomicIntegerTest {
    //volatile 不能保证原子性
    private static volatile int value;

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        /*
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int temp = value;
                    System.out.println(Thread.currentThread().getName() + " " + temp);
                    value += 1;
                    x++;
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int temp = value;
                    System.out.println(Thread.currentThread().getName() + " " + temp);
                    value += 1;
                    x++;
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int temp = value;
                    System.out.println(Thread.currentThread().getName() + " " + temp);
                    value += 1;
                    x++;
                }
            }
        };
        */

        AtomicInteger value = new AtomicInteger();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int temp = value.getAndIncrement();
                    set.add(temp);
                    System.out.println(Thread.currentThread().getName() + " " + temp);

                    x++;
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int temp = value.getAndIncrement();
                    set.add(temp);
                    System.out.println(Thread.currentThread().getName() + " " + temp);

                    x++;
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int temp = value.getAndIncrement();
                    set.add(temp);
                    System.out.println(Thread.currentThread().getName() + " " + temp);

                    x++;
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(set.size());
    }
}
