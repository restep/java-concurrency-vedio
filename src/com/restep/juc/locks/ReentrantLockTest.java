package com.restep.juc.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author restep
 * @date 2019/5/18
 */
public class ReentrantLockTest {
    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {

    }

    public void needLock() {
        try {
            LOCK.lock();

            //TimeUnit.SECONDS.sleep(10);
        } finally {
            LOCK.unlock();
        }
    }

}
