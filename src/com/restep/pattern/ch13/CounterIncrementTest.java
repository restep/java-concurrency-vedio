package com.restep.pattern.ch13;

/**
 * two phase termination 模式
 * @author restep
 * @date 2019/5/14
 */
public class CounterIncrementTest {
    public static void main(String[] args) {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        counterIncrement.close();
    }
}
