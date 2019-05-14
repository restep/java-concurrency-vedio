package com.restep.pattern.ch10;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author restep
 * @date 2019/5/14
 */
public class ProducerThread extends Thread {
    private final MessageQueue messageQueue;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public ProducerThread(MessageQueue messageQueue, int seq) {
        super("producer-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = new Message("message-" + COUNTER.getAndIncrement());
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName() + " put message " + message.getData());

                Thread.sleep(RANDOM.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
