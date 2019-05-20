package com.restep.juc.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/20
 */
public class WorkStealingPoolTest {
    public static void main(String[] args) throws InterruptedException {
        /*
        Optional.of(Runtime.getRuntime().availableProcessors())
                .ifPresent(System.out::println);
                */
        ExecutorService executorService = Executors.newWorkStealingPool();

        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i ->
                (Callable<String>) () -> {
                    System.out.println("Thread " + Thread.currentThread().getName());
                    sleep(2);
                    return "Task-" + i;
                }
        ).collect(Collectors.toList());

        executorService.invokeAll(callableList).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);
    }

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
