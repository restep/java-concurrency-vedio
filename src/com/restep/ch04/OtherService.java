package com.restep.ch04;

/**
 * @author restep
 * @date 2019/5/10
 */
public class OtherService {
    private DeadLock deadLock;
    private final Object LOCK = new Object();

    public void s1() {
        synchronized (LOCK) {
            System.out.println("s1()");
        }
    }

    public void s2() {
        synchronized (LOCK) {
            System.out.println("s2()");
            deadLock.m1();
        }
    }

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }
}
