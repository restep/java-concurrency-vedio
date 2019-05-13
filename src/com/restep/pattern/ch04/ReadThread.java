package com.restep.pattern.ch04;

/**
 * @author restep
 * @date 2019/5/13
 */
public class ReadThread extends Thread {
    private final Data data;

    public ReadThread(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuffer = data.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuffer));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
