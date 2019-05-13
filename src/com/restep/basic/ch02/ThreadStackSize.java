package com.restep.basic.ch02;

/**
 * 虚拟机栈
 * Thread(ThreadGroup group, Runnable target, String name, long stackSize)
 *
 * @author restep
 * @date 2019/5/9
 */
public class ThreadStackSize {
    private static int counter = 1;

    public static void main(String[] args) {
        Thread thread = new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);
                } catch (Error e) {
                    System.out.println(counter);
                    e.printStackTrace();
                }
            }

            private void add(int i) {
                counter++;
                add(i + 1);
            }
        }, "myThread", 1 << 24);
        thread.start();
    }
}
