package com.restep.pattern.ch11;

/**
 * @author restep
 * @date 2019/5/14
 */
public class CountDown {
    private final int total;

    private int counter = 0;

    public CountDown(int total) {
        this.total = total;
    }

    public void countDown() {
        synchronized (this) {
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != total) {
                this.wait();
            }
        }
    }
}
