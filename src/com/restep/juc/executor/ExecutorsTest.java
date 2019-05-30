package com.restep.juc.executor;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/19
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        useFixedSizePool();
    }

    private static void useSinglePool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " [" + i + "]");
        }));

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void useFixedSizePool() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " [" + i + "]");
        }));

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }

    private static void useCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("getActiveCount(): " + ((ThreadPoolExecutor) executorService).getActiveCount());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("==================");
            }
        });

        System.out.println("getActiveCount(): " + ((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " [" + i + "]");
        }));

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("getActiveCount(): " + ((ThreadPoolExecutor) executorService).getActiveCount());
    }
}
