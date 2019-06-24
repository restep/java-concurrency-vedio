package com.restep.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author restep
 * @date 2019/6/24
 */
class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        TimeUnit.SECONDS.sleep(3L);

        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }

        return sum;
    }
}
