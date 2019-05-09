package com.restep.ch01;

/**
 * start() 和 run() 的区别
 * start() 采用了模板方法设计模式
 * @author restep
 * @date 2019/5/9
 */
public class StartAndRun {
    public static void main(String[] args) {
        Thread thread = new Thread("read-thread") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        /*
        不能重复调用start()两次
        thread.start();
        thread.start();
        */

        //并未真正启动线程 只是调用了run()方法而已
        thread.run();
    }
}
