package com.restep.pattern.ch07;

import java.util.Random;

/**
 * @author restep
 * @date 2019/5/14
 */
public class ServerThread extends Thread {
    private final RequestQueue requestQueue;

    private final Random random;

    private volatile boolean flag = true;

    public ServerThread(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;

        random = new Random();
    }

    @Override
    public void run() {
        while (!flag) {
            Request request = requestQueue.getRequest();
            if (null == request) {
                System.out.println("receive the empty request");
                continue;
            }

            System.out.println("server -> " + request.getValue());

            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                return;
            }
        }
    }


    public void close() {
        this.flag = true;
        this.interrupt();
    }
}
