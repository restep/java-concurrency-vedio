package com.restep.basic.ch04;

/**
 * @author restep
 * @date 2019/5/10
 */
public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    deadLock.m1();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    while (true) {
                        otherService.s2();
                    }
                }
            }
        }.start();
    }
}
