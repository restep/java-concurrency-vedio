package com.restep.juc.executor;

import java.util.concurrent.*;

/**
 * @author restep
 * @date 2019/5/19
 */
public class ThreadPoolExecutorBuild {
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) buildThreadPoolExecutor();
        int activeCount = -1;
        int queueSize = -1;

        while (true) {
            if (activeCount != executorService.getActiveCount()
                    || queueSize != executorService.getQueue().size()) {
                System.out.println("ActiveCount: " + executorService.getActiveCount());
                System.out.println("CorePoolSize: " + executorService.getCorePoolSize());
                System.out.println("QueueSize: " + executorService.getQueue().size());
                System.out.println("MaximumPoolSize: " + executorService.getMaximumPoolSize());

                activeCount = executorService.getActiveCount();
                queueSize = executorService.getQueue().size();
                System.out.println("==================================");
            }
        }
    }

    private static ExecutorService buildThreadPoolExecutor() {
        ExecutorService executorService = new ThreadPoolExecutor(
                1, 2, 30,
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
        System.out.println("the ThreadPoolExecutor create done");

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                sleepSeconds(100L);
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                sleepSeconds(100L);
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                sleepSeconds(100L);
            }
        });

        return executorService;
    }

    private static void sleepSeconds(long seconds) {
        try {
            System.out.println("* " + Thread.currentThread().getName() + " *");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
