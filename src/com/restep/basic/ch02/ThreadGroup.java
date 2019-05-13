package com.restep.basic.ch02;

import java.util.Arrays;

/**
 * 线程组
 * @author restep
 * @date 2019/5/9
 */
public class ThreadGroup {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        /*
        System.out.println(thread.getThreadGroup().getName());

        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        */
        java.lang.ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.activeCount());

        Thread[] threadArr = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threadArr);

        /**
         * Thread[main,5,main]
         Thread[Monitor Ctrl-Break,5,main]  监听线程
         Thread[Thread-0,5,main]
         */
        Arrays.asList(threadArr).forEach(System.out::println);
    }
}
