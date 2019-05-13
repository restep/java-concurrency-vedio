package com.restep.basic.ch06;

import java.util.Arrays;

/**
 * 创建线程组
 *
 * @author restep
 * @date 2019/5/11
 */
public class ThreadGroupCreate {
    public static void main(String[] args) {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                while (true) {
                    try {
                        /*System.out.println(this.getThreadGroup().getName());
                        System.out.println(this.getThreadGroup().getParent());
                        System.out.println(this.getThreadGroup().getParent().activeCount());*/
                        Thread.sleep(10_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();

        ThreadGroup tg2 = new ThreadGroup("TG2");
        Thread t2 = new Thread(tg2, "t2") {
            @Override
            public void run() {
                //tg1和tg2共同的父线程组是main
                System.out.println(tg1.getName());
                Thread[] threadArr = new Thread[tg1.activeCount()];
                tg1.enumerate(threadArr);

                Arrays.asList(threadArr).forEach(System.out::println);
            }
        };
        t2.start();
    }
}
