package com.restep.basic.ch03;

/**
 * 创建一个object调用wait()
 * @author restep
 * @date 2019/5/10
 */
public class ThreadInterruptMonitorWait {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (MONITOR) {
                        try {
                            MONITOR.wait(10);
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
