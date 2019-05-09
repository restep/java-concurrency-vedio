package com.restep.ch03;

import java.util.stream.IntStream;

/**
 * thread.join 会将现有线程执行完毕后  才执行后续线程
 * @author restep
 * @date 2019/5/10
 */
public class ThreadJoin {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                IntStream.range(1, 1000)
                        .forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
            }
        }, "first");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                IntStream.range(1, 1000)
                        .forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
            }
        }, "second");


        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IntStream.range(1, 1000)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
    }
}
