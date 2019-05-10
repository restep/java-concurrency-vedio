package com.restep.ch05;

/**
 * 生产和消费之间进行通讯
 * 生产一个就消费掉一个
 * wait() notify()
 *
 * @author restep
 * @date 2019/5/10
 */
public class ProduceConsumer2 {
    private int i = 1;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    /**
     * 生产
     */
    private void produce() {
        synchronized (LOCK) {
            //如果已经生产过了 则等待消费者消费
            if (isProduced) {
                try {
                    //Object的wait方法
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("produce() " + i);

                //通知消费者 已生产
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    /**
     * 消费
     */
    private void consume() {
        synchronized (LOCK) {
            //如果已经生产了 就消费
            if (isProduced) {
                System.out.println("consume() " + i);

                //通知生产者 已消费掉
                LOCK.notify();
                isProduced = false;
            } else {
                try {
                    //等待生产者生产
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumer2 pc = new ProduceConsumer2();

        new Thread("produce") {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                }

            }
        }.start();

        new Thread("consume") {
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start();
    }
}
