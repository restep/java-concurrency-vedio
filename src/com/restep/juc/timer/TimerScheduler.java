package com.restep.juc.timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author restep
 * @date 2019/5/20
 */
public class TimerScheduler {
    public static void main(String[] args) {
        Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("=========" + System.currentTimeMillis());

                try {
                    TimeUnit.SECONDS.sleep(7L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.schedule(task, 1_000L, 1_000L);
    }
}
