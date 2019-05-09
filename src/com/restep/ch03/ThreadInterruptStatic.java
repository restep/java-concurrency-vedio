package com.restep.ch03;

/**
 * @author restep
 * @date 2019/5/10
 */
public class ThreadInterruptStatic {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + ": " + Thread.interrupted());
                        e.printStackTrace();
                    }
                }
            }
        }, "myThread");
        thread.start();

        try {
            Thread.sleep(1_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.getName() + " interrupt: " + thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.getName() + " interrupt: " + thread.isInterrupted());
    }
}
