package com.restep.juc.locks;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/18
 */
public class ReentrantLockTest {
    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        /*
        IntStream.range(0, 2).forEach(i -> new Thread() {
            @Override
            public void run() {
                needLockBySync();
            }
        }.start());

        Thread t1 = new Thread() {
            @Override
            public void run() {
                testUnInterruptibly();
            }
        };
        t1.start();

        TimeUnit.SECONDS.sleep(1L);

        Thread t2 = new Thread() {
            @Override
            public void run() {
                testUnInterruptibly();
            }
        };
        t2.start();

        TimeUnit.SECONDS.sleep(1L);

        t2.interrupt();
        System.out.println("===========");
        */

        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                testTryLock();
            }
        };
        t1.start();

        TimeUnit.SECONDS.sleep(1L);

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                testTryLock();
            }
        };
        t2.start();

    }

    public static void needLock() {
        try {
            LOCK.lock();
            Optional.of("the thread-" + Thread.currentThread().getName() + " get lock and will do working...")
                    .ifPresent(System.out::println);

            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    public static void testUnInterruptibly() {
        try {
            LOCK.lockInterruptibly();
            Optional.of("the thread-" + Thread.currentThread().getName() + " get lock and will do working...")
                    .ifPresent(System.out::println);

            while (true) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    public static void testTryLock() {
        if (LOCK.tryLock()) {
            try {
                Optional.of("the thread-" + Thread.currentThread().getName() + " get lock and will do working...")
                        .ifPresent(System.out::println);

                while (true) {

                }
            } finally {
                LOCK.unlock();
            }
        } else {
            Optional.of("the thread-" + Thread.currentThread().getName() + " not get lock")
                    .ifPresent(System.out::println);
        }
    }

    public static void needLockBySync() {
        synchronized (ReentrantLock.class) {
            try {
                Optional.of("the thread-" + Thread.currentThread().getName() + " get lock and will do working...")
                        .ifPresent(System.out::println);

                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
