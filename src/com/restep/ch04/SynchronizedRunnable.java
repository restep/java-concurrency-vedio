package com.restep.ch04;

/**
 * synchronized尽量给范围小的区域加锁
 *
 * @author restep
 * @date 2019/5/10
 */
public class SynchronizedRunnable implements Runnable {
    private int current = 1;
    //只读的共享数据 不用担心线程安全
    private final int MAX = 500;

    @Override
    public void run() {
        while (true) {
            if (ticket()) {
                break;
            }
        }
    }

    /**
     * 方法上加synchronized 等同于synchronized(this) 或者 synchronized(SynchronizedRunnable.class)
     *
     * @return
     */
    private synchronized boolean ticket() {
        //1.读current
        if (current > MAX) {
            return true;
        }

        try {
            Thread.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //2.current = current + 1
        //3.放回current
        System.out.println(Thread.currentThread().getName() + " 的号码是: " + current++);

        return false;
    }

    public static void main(String[] args) {
        SynchronizedRunnable runnable = new SynchronizedRunnable();
        Thread thread1 = new Thread(runnable, "1号柜台");
        Thread thread2 = new Thread(runnable, "2号柜台");
        Thread thread3 = new Thread(runnable, "3号柜台");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
