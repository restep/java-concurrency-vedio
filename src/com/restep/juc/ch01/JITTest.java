package com.restep.juc.ch01;

/**
 * @author restep
 * @date 2019/5/16
 */
public class JITTest {
    private static boolean init = false;

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                while (!init) {
                    //init 不加volatile 这里加个sout 就可以退出了。。。
                    //JIT编译器做了优化 非volatile修饰的变量
                    System.out.println("aa");
                }
            }
        }.start();

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread() {
            @Override
            public void run() {
                init = true;
                System.out.println("update init to true");
            }
        }.start();
    }
}
