package com.restep.juc.executor;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/28
 */
public class ThreadPoolExecutorLongTimeTask {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                10, 20, 30,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setDaemon(true);
                        return thread;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );

        IntStream.range(0, 10).boxed().forEach(i -> {
            executorService.submit(() -> {
                while (true) {

                }
            });
        });

        executorService.shutdownNow();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==============================");
    }
}
