package com.restep.juc.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/19
 */
public class PhaserTest {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final Phaser phaser = new Phaser();
        IntStream.rangeClosed(1, 5).boxed()
                .map(i -> phaser).forEach(Task::new);

        phaser.register();
        phaser.arriveAndAwaitAdvance();

        System.out.println("all of worker finished the task");
    }

    static class Task extends Thread {
        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register();
            start();
        }

        @Override
        public void run() {
            System.out.println("the worker [" + this.getName() + "] is working...");

            try {
                TimeUnit.SECONDS.sleep(RANDOM.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            phaser.arriveAndAwaitAdvance();
        }
    }
}
