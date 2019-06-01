package com.restep.basic.ch01;

/**
 * @author restep
 * @date 2019/6/1
 */
public class BussB extends Thread {
    LockMethod lockMethod;
    void deal(LockMethod lockMethod){
        this.lockMethod = lockMethod;
    }

    @Override
    public void run() {
        super.run();
        lockMethod.busiB();
    }
}
