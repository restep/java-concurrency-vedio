package com.restep.pattern.ch01;

/**
 * 饿汉式
 *
 * @author restep
 * @date 2019/5/12
 */
public class EagerSingleton {
    //classLoader 类一加载就会实例化instance  如果长时间不使用instance会导致资源浪费
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {

    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
