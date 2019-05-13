package com.restep.pattern.ch06;

/**
 * @author restep
 * @date 2019/5/14
 */
public interface Future<T> {
    T get() throws InterruptedException;
}
