package com.restep.ch04;

/**
 * 线程安全
 *
 * @author restep
 * @date 2019/5/9
 */
public class TicketWindowThreadSafe implements Runnable {
    private int current = 1;
    private int max = 500;
    private Object lock = new Object();

    @Override
    public void run() {
        while (true) {
            /**
             * synchronized在while循环内外结果会不一样
             * 如果循环外 锁定的是this
             */
            synchronized (lock) {
                //在synchronized代码块中是单线程
                if (current > max) {
                    break;
                }

                try {
                    Thread.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 的号码是: " + current++);
            }
        }

    }

    public static void main(String[] args) {
        TicketWindowThreadSafe ticketWindow = new TicketWindowThreadSafe();
        Thread thread1 = new Thread(ticketWindow, "1号柜台");
        Thread thread2 = new Thread(ticketWindow, "2号柜台");
        Thread thread3 = new Thread(ticketWindow, "3号柜台");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
