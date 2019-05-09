package com.restep.ch02;

/**
 * 守护线程
 *
 * @author restep
 * @date 2019/5/9
 */
public class DaemonThread {
    public static void main(String[] args) {
        //线程为new状态
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    //blocked状态
                    Thread.sleep(100_000L);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        //main线程生命周期结束 守护线程不管是什么状态 也随即结束
        thread.setDaemon(true);
        //线程为Runnable状态(不是一定会执行 有CPU决定) --> Blocked Waiting | Timed Waiting --> Terminated
        thread.start();

        try {
            //JDK1.7的新特性
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
