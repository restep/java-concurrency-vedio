package com.restep.ch02;

/**
 * 分析创建线程
 * @author restep
 * @date 2019/5/9
 */
public class CreateThread {
    public static void main(String[] args) {
        Thread thread01 = new Thread();
        thread01.start();

        Thread thread02 = new Thread();
        thread02.start();

        System.out.println(thread01.getName());
        System.out.println(thread02.getName());
    }
}
