package com.restep.basic.ch03;

import java.util.Optional;

/**
 * 线程优先级
 *
 * @author restep
 * @date 2019/5/10
 */
public class ThreadPriority {
    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    Optional.of(Thread.currentThread().getName() + "-index" + i).ifPresent(System.out::println);
                }
            }
        };
        thread1.setPriority(Thread.MAX_PRIORITY);

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    Optional.of(Thread.currentThread().getName() + "-index" + i).ifPresent(System.out::println);
                }
            }
        };
        thread2.setPriority(Thread.NORM_PRIORITY);

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    Optional.of(Thread.currentThread().getName() + "-index" + i).ifPresent(System.out::println);
                }
            }
        };
        thread3.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}


