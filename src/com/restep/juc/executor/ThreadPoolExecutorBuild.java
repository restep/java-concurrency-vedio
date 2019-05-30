package com.restep.juc.executor;

import java.util.concurrent.*;

/**
 * @author restep
 * @date 2019/5/19
 */
public class ThreadPoolExecutorBuild {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) buildThreadPoolExecutor();

        int activeCount = -1;
        int queueSize = -1;

        while (true) {
            if (activeCount != threadPoolExecutor.getActiveCount()
                    || queueSize != threadPoolExecutor.getQueue().size()) {
                System.out.println("getActiveCount(): " + threadPoolExecutor.getActiveCount());
                System.out.println("getCorePoolSize(): " + threadPoolExecutor.getCorePoolSize());
                System.out.println("getQueue().size(): " + threadPoolExecutor.getQueue().size());
                System.out.println("getMaximumPoolSize(): " + threadPoolExecutor.getMaximumPoolSize());

                activeCount = threadPoolExecutor.getActiveCount();
                queueSize = threadPoolExecutor.getQueue().size();
                System.out.println("================================");
            }
        }
    }

    private static ExecutorService buildThreadPoolExecutor() {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);

                return thread;
            }
        }, new ThreadPoolExecutor.AbortPolicy());
        System.out.println("The ThreadPoolExecutor create done");

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                sleepSeconds(100L);
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                sleepSeconds(10L);
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                sleepSeconds(10L);
            }
        });

        return executorService;
    }

    private static void sleepSeconds(long seconds) {
        try {
            System.out.println(">>>" + Thread.currentThread().getName() + "<<<");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
