package com.restep.pattern.ch01;

import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/12
 */
public class EnumSingleton {
    private EnumSingleton() {

    }

    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    /**
     * 枚举是线程安全的 构造函数只会装载一次
     */
    private enum Singleton {
        INSTANCE;

        private final EnumSingleton instance;

        Singleton() {
            instance = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return instance;
        }
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).forEach(i -> new Thread(String.valueOf(i)) {
            @Override
            public void run() {
                System.out.println(EnumSingleton.getInstance());
            }
        }.start());
    }
}
