package com.restep.basic.ch04;

/**
 * this锁
 * @author restep
 * @date 2019/5/10
 */
public class SynchronizedThisLock {
    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();

        new Thread("t1") {
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();
        new Thread("t2") {
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();
    }
}

/**
 * m1()和m2()都是锁住this 所以一个方法抢到this锁后 另一个方法需要等待 直到上个方法释放this锁
 */
class ThisLock {
    public synchronized void m1() {
        try {
            System.out.println("m1: " + Thread.currentThread().getName());
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2() {
        try {
            System.out.println("m2: " + Thread.currentThread().getName());
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
