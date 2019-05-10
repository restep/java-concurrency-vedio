package com.restep.ch05;

/**
 * sleep()和wait()的区别
 *
 * @author restep
 * @date 2019/5/10
 */
public class SleepAndWait {
    //定义锁 最好用final
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        for (int i = 1; i < 3; i++) {
            new Thread("t" + i) {
                @Override
                public void run() {
                    m2();
                }
            }.start();
        }
    }

    /**
     * t1 t2线程先后进入
     */
    public static void m1() {
        synchronized (LOCK) {
            //如果LOCK不是final 被改变会导致错误
            try {
                System.out.println(Thread.currentThread().getName() + " enter");
                Thread.sleep(3_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * t1 t2线程同时进入 出现假死 等待被唤醒
     */
    public static void m2() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName() + " enter");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
