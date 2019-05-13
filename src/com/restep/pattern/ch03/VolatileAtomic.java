package com.restep.pattern.ch03;

/**
 * volatile
 * 并未保证原子性
 *
 * @author restep
 * @date 2019/5/12
 */
public class VolatileAtomic {
    private static volatile int initValue = 0;

    private static final int MAX_LIMIT = 50;

    public static void main(String[] args) {
        new Thread(() -> {
            while (initValue < MAX_LIMIT) {
                System.out.println("adder-1: " + ++initValue);
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "adder-1").start();


        new Thread(() -> {
            while (initValue < MAX_LIMIT) {
                System.out.println("adder-2: " + ++initValue);
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "adder-2").start();
    }
}
