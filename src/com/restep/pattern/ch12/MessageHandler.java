package com.restep.pattern.ch12;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author restep
 * @date 2019/5/14
 */
public class MessageHandler {
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(5);

    public void request(Message message) {
        EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                String value = message.getValue();

                try {
                    Thread.sleep(RANDOM.nextInt(1_000));

                    System.out.println(Thread.currentThread().getName() + " value: " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void shutdown() {
        ExecutorService executorService = (ExecutorService) EXECUTOR;
        executorService.shutdown();
    }
}
