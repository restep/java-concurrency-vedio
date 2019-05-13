package com.restep.basic.ch02;

/**
 * 分析创建线程
 *
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

        Thread thread03 = new Thread("myThreadFirst");
        Thread thread04 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread04");
            }
        });

        //传递了名字 JVM就不会再生成Thread-0 Thread-1这样的默认名称
        System.out.println(thread03.getName());
        System.out.println(thread04.getName());

        Thread thread05 = new Thread(() -> {
            System.out.println("thread05");
        }, "myThreadSecond");
        System.out.println(thread05.getName());
    }
}
