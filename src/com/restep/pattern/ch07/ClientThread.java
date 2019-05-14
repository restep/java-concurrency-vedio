package com.restep.pattern.ch07;

import java.util.Random;

/**
 * @author restep
 * @date 2019/5/14
 */
public class ClientThread extends Thread {
    private final RequestQueue requestQueue;

    private final Random random;

    private final String sendValue;

    public ClientThread(RequestQueue requestQueue, String sendValue) {
        this.requestQueue = requestQueue;
        this.sendValue = sendValue;

        random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("client -> request " + sendValue);
            requestQueue.putRequest(new Request(sendValue));

            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
