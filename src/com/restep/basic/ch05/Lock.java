package com.restep.basic.ch05;

import java.util.Collection;
import java.util.List;

/**
 * @author restep
 * @date 2019/5/11
 */
public interface Lock {
    /**
     * 锁
     *
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 带时效的锁
     * 超出时间未抢到锁 就抛异常
     * @param millis
     * @throws InterruptedException
     * @throws TimeoutException
     */
    void lock(long millis) throws InterruptedException, TimeoutException;

    /**
     * 解锁
     */
    void unlock();

    /**
     * 查看阻塞的线程
     *
     * @return
     */
    Collection<Thread> getBlockedThread();

    /**
     * 查看阻塞线程的大小
     *
     * @return
     */
    int getBlockedSize();

    class TimeoutException extends Exception {
        public TimeoutException(String message) {
            super(message);
        }
    }
}
