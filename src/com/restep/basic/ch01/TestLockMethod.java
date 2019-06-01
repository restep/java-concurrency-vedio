package com.restep.basic.ch01;

/**
 * 尽量不要用synchronized锁整个方法
 * @author restep
 * @date 2019/6/1
 */
public class TestLockMethod extends Thread {
    public static void main(String[] args) {
        LockMethod lockMethod = new LockMethod();
        BussA bussa = new BussA();
        BussB bussb = new BussB();

        bussa.deal(lockMethod);
        bussb.deal(lockMethod);
        bussa.start();
        bussb.start();
    }
}
