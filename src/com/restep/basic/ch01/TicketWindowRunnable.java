package com.restep.basic.ch01;

/**
 * 线程非安全
 * TicketWindowThreadSafe 线程安全
 * @author restep
 * @date 2019/5/9
 */
public class TicketWindowRunnable implements Runnable {
    private static int current = 1;
    private static final int MAX = 50;

    @Override
    public void run() {
        while (current <= MAX) {
            System.out.println(Thread.currentThread() + " 的号码是: " + current++);

            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();
        Thread thread1 = new Thread(ticketWindow, "1号柜台");
        Thread thread2 = new Thread(ticketWindow, "2号柜台");
        Thread thread3 = new Thread(ticketWindow, "3号柜台");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
