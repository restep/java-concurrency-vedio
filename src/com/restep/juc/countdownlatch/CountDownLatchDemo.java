package com.restep.juc.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * https://www.jianshu.com/p/f17692e9114f
 * @author restep
 * @date 2019/6/24
 */
public class CountDownLatchDemo implements Runnable {
    private static final CountDownLatch countDownLatch = new CountDownLatch(10);
    private static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        //模拟检查任务
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            System.out.println(Thread.currentThread().getName() + " check complete");

            //计数减1
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(demo);
        }

        //等待检查
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //发射火箭
        System.out.println("fire");

        //关闭线程池
        executorService.shutdown();
    }
}
