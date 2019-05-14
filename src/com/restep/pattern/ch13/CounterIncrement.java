package com.restep.pattern.ch13;

import java.util.Random;

/**
 * @author restep
 * @date 2019/5/14
 */
public class CounterIncrement extends Thread {
    private volatile boolean terminated = false;
    private int counter = 0;
    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!terminated) {
                System.out.println(Thread.currentThread().getName() + " " + counter++);

                Thread.sleep(random.nextInt(1_000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            clean();
        }
    }

    public void close() {
        this.terminated = true;
        this.interrupt();
    }

    private void clean() {
        System.out.println("do clean work counter: " + counter);
    }
}
