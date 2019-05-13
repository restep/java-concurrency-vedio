package com.restep.pattern.ch06;

/**
 * 多线程Future模式
 * Future 代表未来的一个凭据
 * FutureTask 将调用逻辑隔离
 * FutureService 桥接Future和FutureTask
 *
 * @author restep
 * @date 2019/5/14
 */
public class FutureTest {
    public static void main(String[] args) {
        FutureService futureService = new FutureService();
        //System.out::println 回调通知 不再使用future.get()主动去问结果
        futureService.submit(new FutureTask<String>() {
            @Override
            public String call() {
                try {
                    Thread.sleep(8_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "finish";
            }
        }, System.out::println);

        System.out.println("=======");

        /*
        try {
            主动去要结果
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

    private static String get() {
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "finish";
    }
}
