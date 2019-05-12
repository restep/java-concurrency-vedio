package com.restep.pattern.ch01;

/**
 * 双重检测实现单例
 *
 * @author restep
 * @date 2019/5/12
 */
public class DoubleCheckSingleton {
    //volatile告诉JVM不要进行代码优化 不要重排序
    private static volatile DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton() {

    }

    /**
     * 将同步下放到if内部 只有第一次才会同步
     * 由于编译器优化原因和JVM底层内部模型原因 重排序  程序计数器 两个同步块的顺序会调整
     * 偶尔会出问题 可能导致空指针异常 不建议使用
     * 加volatile可以解决此问题
     *
     * @return
     */
    public static DoubleCheckSingleton getInstance() {
        if (null == instance) {
            synchronized (DoubleCheckSingleton.class) {
                if (null == instance) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }

        return instance;
    }
}
