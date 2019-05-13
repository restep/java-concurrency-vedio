package com.restep.basic.ch05;

import java.util.Optional;

/**
 * @author restep
 * @date 2019/5/11
 */
public class SynchronizedProblem {
    public static void main(String[] args) {
        //t1会一直占用着锁
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                SynchronizedProblem.run();
            }
        };
        t1.start();

        try {
            Thread.sleep(1_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                SynchronizedProblem.run();
            }
        };
        t2.start();

        //t2等待一段时间后
        try {
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();
        System.out.println(t1.isInterrupted());
        System.out.println(t2.isInterrupted());
    }

    private synchronized static void run() {
        Optional.of(Thread.currentThread().getName()).ifPresent(System.out::println);
        while (true) {

        }
    }
}
