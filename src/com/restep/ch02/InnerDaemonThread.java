package com.restep.ch02;

/**
 * 线程内部的守护线程
 * 父线程结束时 内部守护线程不管是什么状态 也随即结束
 * 常用于心跳检查
 *
 * @author restep
 * @date 2019/5/9
 */
public class InnerDaemonThread {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Thread innnerThread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                System.out.println("心跳检查");
                                Thread.sleep(1_000L);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                innnerThread.setDaemon(true);
                innnerThread.start();

                try {
                    Thread.sleep(1_000L);
                    System.out.println("thread end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
