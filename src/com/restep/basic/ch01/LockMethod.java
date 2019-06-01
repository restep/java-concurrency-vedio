package com.restep.basic.ch01;

/**
 * @author restep
 * @date 2019/6/1
 */
public class LockMethod {
    public synchronized void busiA() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " deal with bussiness A: "+i);
        }
    }
    public synchronized void busiB() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " deal with bussiness B: "+i);
        }
    }
}
