package com.restep.pattern.ch06;

/**
 * @author restep
 * @date 2019/5/14
 */
public class AsynFuture<T> implements Future<T> {
    private volatile boolean done = false;
    private T result;

    public void done(T result) {
        synchronized (this) {
            this.result = result;
            this.done = true;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (!done) {
                this.wait();
            }
        }

        return result;
    }
}