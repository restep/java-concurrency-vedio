package com.restep.pattern.ch02;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/12
 */
public class WaitSet {
    private static final Object LOCK = new Object();

    private static void work() {
        synchronized (LOCK) {
            System.out.println("Begin...");

            try {
                System.out.println("Thread will coming");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread will out.");
        }
    }

    /**
     * 1.所有的对象都会有一个wait set 存放调用了该对象wait方法之后进入block状态的线程
     * 2.线程被notify之后 不一定立即得到执行
     * 3.线程从wait set中被唤醒顺序不一定是FIFO
     * 4.线程被唤醒后 必须重新获取锁
     *
     * @param args
     */
    public static void main(String[] args) {
        /*
        IntStream.rangeClosed(1, 10).forEach(i ->
                new Thread(String.valueOf(i)) {
                    @Override
                    public void run() {
                        synchronized (LOCK) {
                            try {
                                Optional.of(Thread.currentThread().getName() + " will come to wait set")
                                        .ifPresent(System.out::println);

                                LOCK.wait();

                                Optional.of(Thread.currentThread().getName() + " will leave to wait set")
                                        .ifPresent(System.out::println);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start()
        );

        try {
            Thread.sleep(3_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IntStream.rangeClosed(1, 10).forEach(i -> {
                    synchronized (LOCK) {
                        LOCK.notify();

                        try {
                            Thread.sleep(1_000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        */
        new Thread() {
            @Override
            public void run() {
                work();
            }
        }.start();

        try {
            Thread.sleep(1_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (LOCK) {
            LOCK.notify();
        }
    }
}
