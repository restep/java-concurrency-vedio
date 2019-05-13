package com.restep.pattern.ch04;

/**
 * 读写分离锁
 * 读的时候 并行
 * 写的时候 串行
 *
 * @author restep
 * @date 2019/5/13
 */
public class ReadWriteLock {
    //读的线程数量
    private int read = 0;
    //写的线程数量
    private int write = 0;
    //等待写的线程数量
    private int toWrite = 0;
    //写的操作是否更多
    private boolean preferWrite = true;

    public ReadWriteLock() {

    }

    public ReadWriteLock(boolean preferWrite) {
        this.preferWrite = preferWrite;
    }

    public synchronized void readLock() throws InterruptedException {
        //存在写线程 则等待
        while (write > 0 || (preferWrite && toWrite > 0)) {
            this.wait();
        }

        //如果不存在正在写的线程 则读线程+1
        this.read++;
    }

    public synchronized void readUnlock() {
        this.read--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        this.toWrite++;

        try {
            //有正在读的线程或者有正在写的线程 则等待
            while (read > 0 || write > 0) {
                this.wait();
            }

            this.write++;
        } finally {
            this.toWrite--;
        }
    }

    public synchronized void writeUnlock() {
        this.write--;
        this.notifyAll();
    }
}
