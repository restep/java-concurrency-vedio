package com.restep.basic.ch03;

import java.util.Optional;

/**
 * 线程ID 线程优先级
 *
 * @author restep
 * @date 2019/5/10
 */
public class ThreadApi {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Optional.of("Hello").ifPresent(System.out::println);

                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        thread.start();

        Optional.of(thread.getName()).ifPresent(System.out::println);
        Optional.of(thread.getId()).ifPresent(System.out::println);
        Optional.of(thread.getPriority()).ifPresent(System.out::println);

        Optional.of(Thread.currentThread().getPriority()).ifPresent(System.out::println);
    }
}
