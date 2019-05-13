package com.restep.basic.ch06;

/**
 * 线程的run()方法里 没法throws异常
 * Thread[Thread-0,5,main]
 * Thread-0 线程名称
 * 5 线程优先级
 * main 线程组
 *
 * @author restep
 * @date 2019/5/11
 */
public class ThreadException {
    private static final int A = 10;
    private static final int B = 0;

    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100L);
                    int result = A / B;
                    System.out.println(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t.setUncaughtExceptionHandler((thread, e) -> {
            System.out.println(e);
            System.out.println(thread);
        });

        t.start();
    }
}
