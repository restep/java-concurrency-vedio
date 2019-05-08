package com.restep.ch01;

/**
 * cmd jps jconsole 19780
 * 观察线程状态
 * @author restep
 * @date 2019/5/9
 */
public class TryConcurrency {
    public static void main(String[] args) {
        Thread thread = new Thread("custom-thread") {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("aaa" + i);

                    try {
                        this.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("bbb" + i);
        }
    }
}
