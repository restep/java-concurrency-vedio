package com.restep.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author restep
 * @date 2019/5/17
 */
public class CountDownLatchTest2 {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread() {
            @Override
            public void run() {
                System.out.println("do initial working...");
                try {
                    Thread.sleep(1_000L);
                    //await允许一个或多个线程等待
                    countDownLatch.await();
                    System.out.println("do other working...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("asyn prepare for some data");
                try {
                    Thread.sleep(2_000L);
                    System.out.println("data prepare for done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    //await允许一个或多个线程等待
                    countDownLatch.await();
                    System.out.println("release");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Thread.currentThread().join();
    }
}
