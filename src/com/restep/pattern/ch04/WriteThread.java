package com.restep.pattern.ch04;

import java.util.Random;

/**
 * @author restep
 * @date 2019/5/13
 */
public class WriteThread extends Thread {
    private static final Random random = new Random(System.currentTimeMillis());

    private final Data data;

    private final String filter;

    private int index = 0;

    public WriteThread(Data data, String filter) {
        this.data = data;
        this.filter = filter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                data.write(c);
                System.out.println(Thread.currentThread().getName() + " write " + String.valueOf(c));

                Thread.sleep(random.nextInt(1_000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filter.charAt(index);
        index++;

        if (index >= filter.length()) {
            index = 0;
        }

        return c;
    }
}
