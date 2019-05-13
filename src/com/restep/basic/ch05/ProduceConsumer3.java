package com.restep.basic.ch05;

import java.util.stream.Stream;

/**
 * 多生产者和多消费者之间进行通讯
 * notify()
 * 会导致假死 所有线程wait(放弃了CPU执行权)
 * @author restep
 * @date 2019/5/10
 */
public class ProduceConsumer3 {
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
                System.out.println(Thread.currentThread().getName() + " produce() " + i);

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
                System.out.println(Thread.currentThread().getName() + " consume() " + i);

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
        ProduceConsumer3 pc = new ProduceConsumer3();
        Stream.of("produce1", "produce2").forEach(index -> new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                }
            }
        }.start());

        Stream.of("consume1", "consume2").forEach(index -> new Thread() {
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start());


    }
}
