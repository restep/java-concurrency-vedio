package com.restep.juc.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author restep
 * @date 2019/5/17
 */
public class ExchangerTest {
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                try {
                    String value = exchanger.exchange("i come form T-A");
                    System.out.println(Thread.currentThread().getName() + " get value: " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " end");
            }
        }, "==A==").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                try {
                    TimeUnit.SECONDS.sleep(10L);
                    String value = exchanger.exchange("i come form T-B");
                    System.out.println(Thread.currentThread().getName() + " get value: " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " end");
            }
        }, "==B==").start();
    }
}
