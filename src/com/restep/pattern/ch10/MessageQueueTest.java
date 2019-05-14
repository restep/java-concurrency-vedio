package com.restep.pattern.ch10;

/**
 * Producer and Consumer
 * @author restep
 * @date 2019/5/14
 */
public class MessageQueueTest {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();

        new ProducerThread(messageQueue, 1).start();
        new ProducerThread(messageQueue, 2).start();
        new ProducerThread(messageQueue, 3).start();

        new CustomerThread(messageQueue, 1).start();
        new CustomerThread(messageQueue, 2).start();
    }
}
