package com.restep.basic.ch03;

/**
 * 和ThreadInterruptStatic的区别在于
 * isInterrupted()普通方法 Thread.interrupted()是静态方法
 *
 * @author restep
 * @date 2019/5/10
 */
public class ThreadInterrupt {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号");
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        //让main线程休眠一下 以免thread线程还是runnable没有running
        try {
            Thread.sleep(1_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }
}
