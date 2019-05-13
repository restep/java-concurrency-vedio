package com.restep.basic.ch03;

import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/10
 */
public class ThreadJoinSingle {
    public static void main(String[] args) {
        Thread firstThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("firstThread start");
                    Thread.sleep(10_000L);
                    System.out.println("firstThread end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "firstThread");

        firstThread.start();
        try {
            //等待多少毫秒后  再等待多少纳秒
            firstThread.join(1000L, 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IntStream.range(1, 1000)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
    }
}
