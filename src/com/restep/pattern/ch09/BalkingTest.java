package com.restep.pattern.ch09;

/**
 * @author restep
 * @date 2019/5/14
 */
public class BalkingTest {
    public static void main(String[] args) {
        BalkingData balkingData = new BalkingData("D:\\balking.txt", "===BEGIN====");
        new CustomerThread(balkingData).start();
        new WaiterThread(balkingData).start();
    }
}
