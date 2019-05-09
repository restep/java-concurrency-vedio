package com.restep.ch03;

/**
 * 给类加锁
 * TODO 和ThreadInterrupt3的区别
 *
 * @author restep
 * @date 2019/5/10
 */
public class ThreadInterruptClazzWait {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (ThreadInterruptClazzWait.class) {
                        try {
                            wait(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
