package com.restep.basic.ch03;

/**
 * 强制关闭线程
 *
 * @author restep
 * @date 2019/5/10
 */
public class ThreadCloseByForce {
    public static void main(String[] args) {
        ThreadService service = new ThreadService();

        long start = System.currentTimeMillis();

        service.execute(new Runnable() {
            @Override
            public void run() {
                //如果执行时间大于设定的强制结束时间
                /*while (true) {

                }*/

                //如果执行时间小于设定的强制结束时间
                try {
                    Thread.sleep(5_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //设定强制结束时间
        service.shutdown(10_000L);

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
