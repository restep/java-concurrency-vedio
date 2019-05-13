package com.restep.basic.ch02;

/**
 * 线程内存分析
 * Thread(ThreadGroup group, Runnable target, String name, long stackSize)
 *
 * @author restep
 * @date 2019/5/9
 */
public class ThreadJvm {
    private int i = 0;
    private byte[] byteArr = new byte[1024];
    private static int counter = 0;

    /**
     * 创建虚拟机栈
     *
     * @param args
     */
    public static void main(String[] args) {
        //局部变量表
        int j = 0;
        //局部变量表存放引用地址  数组存放在堆中
        int[] arr = new int[1024];

        /**
         * 虚拟机栈
         * java.lang.StackOverflowError
         */
        try {
            add(0);
        } catch (Error e) {
            e.printStackTrace();
            System.out.println(counter);
        }
    }

    private static void add(int i) {
        ++counter;
        add(i + 1);
    }
}
