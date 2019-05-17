package com.restep.juc.ch01;

/**
 * @author restep
 * @date 2019/5/16
 */
public class CampareAndSetTest {
    private static final CampareAndSetLock LOCK = new CampareAndSetLock();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    doSomething2();
                }
            }.start();
        }
    }

    private static void doSomething() {
        synchronized (CampareAndSetTest.class) {
            System.out.println(Thread.currentThread().getName() + " get the lock");
            try {
                Thread.sleep(100_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doSomething2() {
        try {
            LOCK.tryLock();
            System.out.println(Thread.currentThread().getName() + " get the lock");
            Thread.sleep(100_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (GetLockException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }

    }
}
