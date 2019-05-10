package com.restep.ch05;

import java.util.*;

/**
 * 线程生产者和消费者综合实战
 * 10台机器 每次只运行5台
 *
 * @author restep
 * @date 2019/5/11
 */
public class CaptureService {
    private static final LinkedList<Control> CONTROLS = new LinkedList<>();
    private static final int MAX_WORKER = 5;

    public static void main(String[] args) {
        List<Thread> worker = new ArrayList<>();

        //10台机器
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10")
                .stream().map(CaptureService::createCaptureThread)
                .forEach(thread -> {
                    thread.start();

                    worker.add(thread);
                });

        worker.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }

    /**
     * 创建线程
     *
     * @param name
     * @return
     */
    private static Thread createCaptureThread(String name) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                Optional.of("The worker [" + Thread.currentThread().getName() + "] begin capture data")
                        .ifPresent(System.out::println);

                //超过5台机器则等待
                synchronized (CONTROLS) {
                    while (CONTROLS.size() > MAX_WORKER) {
                        try {
                            CONTROLS.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    CONTROLS.addLast(new Control());
                }

                //没有超过5台机器则正常工作
                Optional.of("The worker [" + Thread.currentThread().getName() + "] is working...")
                        .ifPresent(System.out::println);
                try {
                    Thread.sleep(3_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //通知其他线程
                synchronized (CONTROLS) {
                    Optional.of("The worker [" + Thread.currentThread().getName() + "] end capture data")
                            .ifPresent(System.out::println);
                    CONTROLS.removeFirst();
                    CONTROLS.notifyAll();
                }
            }
        }, name);
    }

    /**
     * 什么事都不干 只是做个控制
     */
    private static class Control {

    }
}
