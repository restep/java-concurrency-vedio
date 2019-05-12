package com.restep.pattern.ch01;

/**
 * 懒汉式 需要线程同步
 *
 * @author restep
 * @date 2019/5/12
 */
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {

    }

    public synchronized static LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }

        return instance;
    }
}
