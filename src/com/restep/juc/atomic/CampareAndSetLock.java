package com.restep.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CampareAndSet实现锁
 *
 * @author restep
 * @date 2019/5/16
 */
public class CampareAndSetLock {
    private final AtomicInteger value = new AtomicInteger();
    private Thread thread;

    public void tryLock() throws GetLockException {
        boolean flag = value.compareAndSet(0, 1);
        if (!flag) {
            throw new GetLockException("get the lock fail");
        } else {
            thread = Thread.currentThread();
        }
    }

    public void unlock() {
        if (0 == value.get()) {
            return;
        }

        if (thread == Thread.currentThread()) {
            value.compareAndSet(1, 0);
        }
    }
}
