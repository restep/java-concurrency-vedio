package com.restep.basic.ch06;

/**
 * 线程组API常用方法
 *
 * @author restep
 * @date 2019/5/11
 */
public class ThreadGroupApi {
    public static void main(String[] args) {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
        Thread t2 = new Thread(tg2, "t2") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();

        System.out.println(tg1.activeCount());
        System.out.println(tg1.activeGroupCount());
        t2.checkAccess();
    }
}
