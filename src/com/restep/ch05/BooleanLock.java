package com.restep.ch05;

import java.util.*;

/**
 * @author restep
 * @date 2019/5/11
 */
public class BooleanLock implements Lock {
    //当为true时 表示已经拿到锁 当为false时 表示锁是闲置 其他线程可以来抢这个锁
    private boolean initValue;

    //阻塞的线程列表
    private List<Thread> blockedThreadList = new ArrayList<>();

    private Thread currentThread;

    public BooleanLock() {

    }

    public BooleanLock(boolean initValue) {
        this.initValue = initValue;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        //锁已被占用
        while (initValue) {
            //把当前线程放到阻塞的列表中  等待获取锁
            blockedThreadList.add(Thread.currentThread());
            this.wait();
        }

        //锁没被占用
        //把当前线程从阻塞的列表中移除
        blockedThreadList.remove(Thread.currentThread());
        //修改标识为true 表示当前线程正占用锁
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long millis) throws InterruptedException, TimeoutException {
        if (millis <= 0) {
            throw new RuntimeException("非法参数");
        }

        long remind = millis;
        long endTime = System.currentTimeMillis() + millis;
        //锁已被占用
        while (initValue) {
            if (remind <= 0) {
                throw new TimeoutException("time out");
            }

            //把当前线程放到阻塞的列表中  等待获取锁
            blockedThreadList.add(Thread.currentThread());
            this.wait(millis);

            remind = endTime - System.currentTimeMillis();
        }

        //锁没有被占用
        //修改标识为true 表示当前线程正占用锁
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentThread) {
            this.initValue = false;
            //monitor是BooleanLock这个instance
            Optional.of(Thread.currentThread().getName() + " release the lock monitor").ifPresent(System.out::println);
            //通知其他线程
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        //return blockedThreadList 直接返回不安全 其他线程可能会对它修改
        return Collections.unmodifiableCollection(blockedThreadList);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadList.size();
    }
}
