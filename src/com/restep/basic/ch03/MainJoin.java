package com.restep.basic.ch03;

/**
 * main函数的join
 * @author restep
 * @date 2019/5/10
 */
public class MainJoin {
    public static void main(String[] args) {
        try {
            //main线程一直等待自己结束  会导致线程无限执行下去
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
