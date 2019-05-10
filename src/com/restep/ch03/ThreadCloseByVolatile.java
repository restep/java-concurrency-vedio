package com.restep.ch03;

/**
 * 优雅的关闭线程
 * 通过volatile来关闭线程
 * @author restep
 * @date 2019/5/10
 */
public class ThreadCloseByVolatile {
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.shutdown();
    }

    private static class Worker extends Thread {
        //TODO
        private volatile boolean start = true;

        @Override
        public void run() {
            while (start) {

            }
        }

        public void shutdown() {
            this.start = false;
        }
    }
}
