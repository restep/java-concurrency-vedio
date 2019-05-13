package com.restep.basic.ch05;

/**
 * 生产和消费之间没有通讯
 * 导致生产的没有被消费掉
 *
 * @author restep
 * @date 2019/5/10
 */
public class ProduceConsumer1 {
    private int i = 1;
    private final Object LOCK = new Object();

    /**
     * 生产
     */
    private void produce() {
        synchronized (LOCK) {
            while (true) {
                System.out.println("produce() " + i++);
            }
        }
    }

    /**
     * 消费
     */
    private void consume() {
        synchronized (LOCK) {
            System.out.println("consume() " + i);
        }
    }

    public static void main(String[] args) {
        ProduceConsumer1 pc = new ProduceConsumer1();

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
