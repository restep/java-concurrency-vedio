package com.restep.ch05;

import java.util.stream.Stream;

/**
 * 多生产者和多消费者之间进行通讯
 * notifyAll()
 *
 * @author restep
 * @date 2019/5/10
 */
public class ProduceConsumer4 {
    private int i = 1;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    /**
     * 生产
     */
    private void produce() {
        synchronized (LOCK) {
            //如果已经生产过了 则等待消费者消费
            while (isProduced) {
                try {
                    //Object的wait方法
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            i++;
            System.out.println(Thread.currentThread().getName() + " produce() " + i);

            //通知所有的消费者 已生产
            //唤醒同一个lock的所有wait线程
            LOCK.notifyAll();
            isProduced = true;
        }
    }

    /**
     * 消费
     */
    private void consume() {
        synchronized (LOCK) {
            //如果已经生产了 就消费
            while (isProduced) {
                System.out.println(Thread.currentThread().getName() + " consume() " + i);

                //通知所有的生产者 已消费掉
                LOCK.notifyAll();
                isProduced = false;
            }

            try {
                //等待生产者生产
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumer4 pc = new ProduceConsumer4();
        Stream.of("produce1", "produce2", "produce3").forEach(index -> new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start());

        Stream.of("consume1", "consume2", "consume3", "consume4").forEach(index -> new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start());


    }
}
