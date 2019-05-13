package com.restep.basic.ch03;

/**
 * 打断join
 *
 * @author restep
 * @date 2019/5/10
 */
public class ThreadInterruptJoin {
    public static void main(String[] args) {
        //Java8可以不需要显示申明final
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {

                }
            }
        };
        thread1.start();

        Thread main = Thread.currentThread();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //如果是Java8更早的JDK版本 thread1需要用final修饰
                //thread1.interrupt(); 因为thread1join的是main线程 所以应该打断main线程
                main.interrupt();
                System.out.println("interrupt");
            }
        };
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
