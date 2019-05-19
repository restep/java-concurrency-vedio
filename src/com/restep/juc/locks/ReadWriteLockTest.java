package com.restep.juc.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author restep
 * @date 2019/5/18
 */
public class ReadWriteLockTest {
    private static final ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock(true);
    private static final Lock READ_LOCK = READ_WRITE_LOCK.readLock();
    private static final Lock WRITE_LOCK = READ_WRITE_LOCK.writeLock();

    private static final List<Long> DATAS = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(ReadWriteLockTest::write);
        t1.start();

        TimeUnit.SECONDS.sleep(1L);

        Thread t2 = new Thread(ReadWriteLockTest::read);
        t2.start();
    }

    public static void write() {
        try {
            WRITE_LOCK.lock();

            DATAS.add(System.currentTimeMillis());

            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void read() {
        try {
            READ_LOCK.lock();

            DATAS.forEach(System.out::println);
            TimeUnit.SECONDS.sleep(2L);

            System.out.println(Thread.currentThread().getName() + "=====================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            READ_LOCK.unlock();
        }
    }
}
