package com.restep.basic.ch03;

/**
 * 优雅的关闭线程
 *
 * @author restep
 * @date 2019/5/10
 */
public class ThreadCloseByInterrupt {
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(3_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.interrupt();
    }

    private static class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                if (Thread.interrupted()) {
                    System.out.println(Thread.currentThread().getName() + " interrupted");
                    break;
                }
            }
        }
    }
}
