package com.restep.juc.ch01;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author restep
 * @date 2019/5/16
 */
public class AtomicBooleanFlag {
    private static final AtomicBoolean FlAG = new AtomicBoolean(true);

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                while (FlAG.get()) {
                    try {
                        Thread.sleep(1_000L);
                        System.out.println("working...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("finished");
            }
        }.start();

        try {
            Thread.sleep(3_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FlAG.set(false);
    }


}
