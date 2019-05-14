package com.restep.pattern.ch14;

/**
 * Worker-Thread
 *
 * @author restep
 * @date 2019/5/14
 */
public class WorkerClient {
    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("Alex", channel).start();
        new TransportThread("Jack", channel).start();
        new TransportThread("William", channel).start();
    }
}