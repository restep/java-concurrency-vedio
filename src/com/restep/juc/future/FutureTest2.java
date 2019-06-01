package com.restep.juc.future;

import java.util.concurrent.*;

/**
 * @author restep
 * @date 2019/5/21
 */
public class FutureTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        isDone();
    }

    private static void isDone() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(10L);

                return 10;
            }
        });

        Integer result = future.get();
        System.out.println("result: " + result);
        System.out.println("future.isDone(): " + future.isDone());
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
