package com.restep.ch03;

/**
 * 执行线程生命周期结束 守护线程也随即结束
 *
 * @author restep
 * @date 2019/5/10
 */
public class ThreadService {
    private Thread executeThread;
    private boolean finished = false;

    public void execute(Runnable task) {
        executeThread = new Thread() {
            @Override
            public void run() {
                //将要执行任务的线程设为守护线程
                Thread taskThread = new Thread(task);
                taskThread.setDaemon(true);
                taskThread.start();

                try {
                    //执行线程可能没等到任务线程启动就结束了 所以需要保证任务线程执行完
                    taskThread.join();
                    finished = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }

    /**
     * 等待多长时间后 关闭线程
     *
     * @param millis
     */
    public void shutdown(long millis) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if (System.currentTimeMillis() - currentTime >= millis) {
                System.out.println("任务超时 需要结束");
                executeThread.interrupt();
                break;
            }

            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }

        finished = false;
    }
}
