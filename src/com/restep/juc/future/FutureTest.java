package com.restep.juc.future;

import java.util.concurrent.*;

/**
 * @author restep
 * @date 2019/5/21
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        getWithTimeout();
    }

    private static void get() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(10L);

                return 10;
            }
        });

        System.out.println("======== i will be printed quickly ============");

        Thread callerThread = Thread.currentThread();
        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(3L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                callerThread.interrupt();
            }
        }.start();

        System.out.println("==============" + future.get());
    }

    private static void getWithTimeout() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(10L);
                System.out.println("============");
                return 10;
            }
        });

        Integer result = future.get(5L, TimeUnit.SECONDS);
        System.out.println("result: " + result);
    }
}
