package com.restep.pattern.ch11;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/14
 */
public class JdkCountDown {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        System.out.println("准备多线程处理任务");

        final CountDownLatch countDownLatch = new CountDownLatch(5);

        IntStream.rangeClosed(1, 5).forEach(i ->
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + " is working");
                    try {
                        Thread.sleep(RANDOM.nextInt(1_000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    countDownLatch.countDown();
                }, String.valueOf(i)).start()
        );

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("多线程任务全部结束 准备第二阶段");
        System.out.println("...........");
        System.out.println("结束");
    }
}
