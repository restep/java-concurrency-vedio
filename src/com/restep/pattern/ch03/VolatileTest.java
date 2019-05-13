package com.restep.pattern.ch03;

/**
 * volatile
 * 修改要在读之前
 * 保证了不同线程间的内存可见性
 * 禁止重排序 保证了执行有序性
 * 并未保证原子性
 *
 * @author restep
 * @date 2019/5/12
 */
public class VolatileTest {
    private static volatile int initValue = 0;

    private static final int MAX_LIMIT = 50;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = initValue;
            while (localValue < MAX_LIMIT) {
                if (localValue != initValue) {
                    System.out.printf("reader: the value updated to [%d]\n", initValue);
                    localValue = initValue;
                }
            }

        }, "reader").start();


        new Thread(() -> {
            int localValue = initValue;
            while (initValue < MAX_LIMIT) {
                System.out.printf("writer the value to [%d]\n", ++localValue);
                initValue = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "writer").start();
    }
}
