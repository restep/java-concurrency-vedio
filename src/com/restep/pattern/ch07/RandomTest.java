package com.restep.pattern.ch07;

import java.util.Random;

/**
 * @author restep
 * @date 2019/5/14
 */
public class RandomTest {
    public static void main(String[] args) {
        Random random1 = new Random();
        Random random2 = new Random(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {
            System.out.println(random1.nextInt(1_000) + " : " + random2.nextInt(1_000));
        }
    }
}
