package com.restep.pattern.ch01;

/**
 * 静态内部类实现单例模式
 * @author restep
 * @date 2019/5/12
 */
public class InnerClassSingleton {
    private InnerClassSingleton() {

    }

    public static InnerClassSingleton getInstance() {
        return Instance.instance;
    }

    /**
     * 只有真正调用getInstance()才会加载静态内部类
     * instance是static final保证了线程安全性
     * 兼备了并发高效率和延迟加载的优势
     */
    private static class Instance {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }
}
