package com.restep.basic.ch03;

/**
 * 模拟收集数据 统计数据收集的时间
 * @author restep
 * @date 2019/5/10
 */
public class ThreadJoinCapture {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Thread thread01 = new Thread(new CaptureRunnable("capture01", 1_000L), "thread01");
        Thread thread02 = new Thread(new CaptureRunnable("capture02", 3_000L), "thread02");
        Thread thread03 = new Thread(new CaptureRunnable("capture03", 5_000L), "thread03");

        thread01.start();
        thread02.start();
        thread03.start();

        try {
            thread01.join();
            thread02.join();
            thread03.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("耗时: " + (endTime - startTime) / 1000);

    }
}

class CaptureRunnable implements Runnable {
    private String machineName;
    private long spendTime;

    public CaptureRunnable() {

    }

    public CaptureRunnable(String machineName, long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(spendTime);
            System.out.println(machineName + " completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return machineName + " finish";
    }
}
