package com.restep.juc.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author restep
 * @date 2019/5/17
 */
public class CountDownLatchTest1 {
    private static Random random = new Random(System.currentTimeMillis());

    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static final CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) {
        int[] datas = query();

        for (int i = 0; i < datas.length; i++) {
            executor.execute(new SimpleRunnable(datas, i, countDownLatch));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("all finished");

        executor.shutdown();
    }

    private static int[] query() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    private static class SimpleRunnable implements Runnable {
        private final int[] data;

        private final int index;

        private final CountDownLatch countDownLatch;

        public SimpleRunnable(int[] data, int index, CountDownLatch countDownLatch) {
            this.data = data;
            this.index = index;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int value = data[index];
            if (value % 2 == 0) {
                data[index] = value * 2;
            } else {
                data[index] = value * 10;
            }

            System.out.println(Thread.currentThread().getName() + " finished");

            countDownLatch.countDown();
        }
    }
}
