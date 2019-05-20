package com.restep.juc.executor;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/19
 */
public class ThreadPoolExecutorTask {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                10, 20, 30,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        return thread;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );

        IntStream.range(0, 20).boxed().forEach(i ->
                executorService.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3L);
                        System.out.println(Thread.currentThread().getName() + " [" + i + "] finish done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
        );

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("============================");
    }
}
