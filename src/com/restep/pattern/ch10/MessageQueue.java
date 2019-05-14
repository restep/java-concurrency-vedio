package com.restep.pattern.ch10;

import java.util.LinkedList;

/**
 * @author restep
 * @date 2019/5/14
 */
public class MessageQueue {
    private final LinkedList<Message> queue;

    private static final int DEFAULT_MAX_LIMIT = 100;

    private final int limit;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(int limit) {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public void put(Message message) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() > limit) {
                queue.wait();
            }

            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }

            Message message = queue.removeFirst();
            queue.notifyAll();

            return message;
        }
    }

    public int getMaxLimit() {
        return limit;
    }

    public int getMessageSize() {
        synchronized (queue) {
            return queue.size();
        }
    }
}
