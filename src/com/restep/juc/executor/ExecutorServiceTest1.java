package com.restep.juc.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author restep
 * @date 2019/5/20
 */
public class ExecutorServiceTest1 {
    public static void main(String[] args) {
        isTerminated();
    }

    private static void isShutDown() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("shutdown start: " + executorService.isShutdown());
        executorService.shutdown();
        System.out.println("shutdown end: " + executorService.isShutdown());

        executorService.execute(() -> System.out.println("i will be executed after shutdown?"));
    }

    private static void isTerminated() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();

        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        System.out.println(((ThreadPoolExecutor) executorService).isTerminating());
    }
}
