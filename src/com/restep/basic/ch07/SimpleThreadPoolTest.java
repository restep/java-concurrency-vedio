package com.restep.basic.ch07;

import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/12
 */
public class SimpleThreadPoolTest {
    public static void main(String[] args) {
        SimpleThreadPool threadPool = new SimpleThreadPool();

        IntStream.rangeClosed(0, 40).forEach(i -> {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("the runnable " + i + " be serviced by " + Thread.currentThread() + " start");
                    try {
                        Thread.sleep(3_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("the runnable " + i + " be serviced by " + Thread.currentThread() + " finished");
                }
            });
        });

        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();

        /*
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("========");
            }
        });
        */
    }
}
