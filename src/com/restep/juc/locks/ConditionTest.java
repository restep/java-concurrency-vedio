package com.restep.juc.locks;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author restep
 * @date 2019/5/18
 */
public class ConditionTest {
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition CONDITION = LOCK.newCondition();
    private static int data = 0;
    private static volatile boolean noUsed = true;

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    buildData();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    useData();
                }
            }
        }.start();
    }

    private static void buildData() {
        try {
            LOCK.lock();

            while (noUsed) {
                CONDITION.await();
            }

            data++;

            Optional.of("P: " + data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1L);

            noUsed = true;
            CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    private static void useData() {
        try {
            LOCK.lock();

            while (!noUsed) {
                CONDITION.await();
            }

            TimeUnit.SECONDS.sleep(1L);

            Optional.of("C: " + data).ifPresent(System.out::println);

            noUsed = false;
            CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}
