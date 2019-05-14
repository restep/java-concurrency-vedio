package com.restep.pattern.ch08;

/**
 * @author restep
 * @date 2019/5/14
 */
public class ThreadLocalSimpleTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
             return "ReStep";
        }
    };

    //JVM start main thread
    public static void main(String[] args) {
        //threadLocal.set("abc");

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(threadLocal.get());
    }
}
