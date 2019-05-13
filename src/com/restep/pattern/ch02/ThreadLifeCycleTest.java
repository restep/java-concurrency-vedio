package com.restep.pattern.ch02;

import java.util.Arrays;

/**
 * 使用观察者模式 观察线程的生命周期
 * @author restep
 * @date 2019/5/13
 */
public class ThreadLifeCycleTest {
    public static void main(String[] args) {
         new ThreadLifeCycleListener().concurrentQuery(Arrays.asList("1", "2"));
    }
}
