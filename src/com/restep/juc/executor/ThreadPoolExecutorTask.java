package com.restep.juc.executor;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/19
 */
public class ThreadPoolExecutorTask {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(
                10, 20, 30,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
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

        //shutdown()是非阻塞方法
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("============================");
    }
}
