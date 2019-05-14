package com.restep.pattern.ch10;

import java.util.Random;

/**
 * @author restep
 * @date 2019/5/14
 */
public class CustomerThread extends Thread {
    private final MessageQueue messageQueue;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public CustomerThread(MessageQueue messageQueue, int seq) {
        super("customer-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName() + " take message " + message.getData());

                Thread.sleep(RANDOM.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
