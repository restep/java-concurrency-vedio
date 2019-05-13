package com.restep.pattern.ch04;

/**
 * @author restep
 * @date 2019/5/13
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Data data = new Data(5);

        for (int i = 0; i < 5; i++) {
            new ReadThread(data).start();
        }

        new WriteThread(data, "qwertyuiopasdfg").start();
        new WriteThread(data, "qwertyuiopasdfg".toUpperCase()).start();
    }
}
