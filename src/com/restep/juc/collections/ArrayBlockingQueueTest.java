package com.restep.juc.collections;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author restep
 * @date 2019/6/1
 */
public class ArrayBlockingQueueTest {
    public static void main(String[] args) {
        testAdd();
    }

    public static void testAdd() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        System.out.println(queue.add("aaa"));
        System.out.println(queue.add("aaa"));
        System.out.println(queue.add("aaa"));
        System.out.println(queue.add("aaa"));
        System.out.println(queue.add("aaa"));
        System.out.println(queue.add("aaa"));
    }
}
