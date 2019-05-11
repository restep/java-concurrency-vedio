package com.restep.ch05;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 自己实现一个显示锁
 *
 * @author restep
 * @date 2019/5/11
 */
public class BooleanLockTest {
    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("t1", "t2", "t3", "t4")
                .forEach(name -> new Thread(() -> {
                    try {
                        booleanLock.lock(-100L);

                        Optional.of(Thread.currentThread().getName() + " has lock monitor")
                                .ifPresent(System.out::println);
                        Optional.of(Thread.currentThread().getName() + " is working")
                                .ifPresent(System.out::println);
                        Thread.sleep(3_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Lock.TimeoutException e) {
                        Optional.of(Thread.currentThread().getName() + " time out").ifPresent(System.out::println);
                        e.printStackTrace();
                    } finally {
                        booleanLock.unlock();
                    }
                }, name).start());
    }
}
