package com.restep.basic.ch04;

/**
 * 死锁
 * jstack 可以分析死锁的产生原因
 * Found 1 deadlock.
 * @author restep
 * @date 2019/5/10
 */
public class DeadLock {
    private OtherService otherService;
    private final Object LOCK = new Object();

    public DeadLock() {

    }

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    public void m1() {
        synchronized (LOCK) {
            System.out.println("m1()");
            otherService.s1();
        }
    }

    public void m2() {
        synchronized (LOCK) {
            System.out.println("m2()");
        }
    }
}
