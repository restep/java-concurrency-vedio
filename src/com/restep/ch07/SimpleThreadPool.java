package com.restep.ch07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 自定义简易线程池
 * 任务队列
 * 拒绝策略(抛出异常 | 直接丢弃 | 阻塞 | 临时队列)
 * 线程池大小 最大线程数量限制  min >= active >= max
 *
 * @author restep
 * @date 2019/5/11
 */
public class SimpleThreadPool {
    private final int SIZE;

    private static final int DEFAULT_SIZE = 10;

    private static volatile int seq = 0;

    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private static final ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    private static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private static final List<WorkTask> THREAD_QUEUE = new ArrayList<>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.SIZE = size;
        init();
    }

    private void init() {
        for (int i = 0; i < SIZE; i++) {
            createWorkThread();
        }
    }

    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE) {
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    private void createWorkThread() {
        WorkTask task = new WorkTask(GROUP, THREAD_PREFIX + seq++);
        task.start();

        THREAD_QUEUE.add(task);
    }

    private enum TaskState {
        FREE,
        RUNNING,
        BLOCKED,
        DEAD
    }

    private static class WorkTask extends Thread {
        private volatile TaskState taskState = TaskState.FREE;

        public WorkTask() {

        }

        public WorkTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public TaskState getTaskState() {
            return this.taskState;
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;

                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;

                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break OUTER;
                        }
                    }

                    runnable = TASK_QUEUE.removeFirst();
                }

                if (null != runnable) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool threadPool = new SimpleThreadPool();

        IntStream.rangeClosed(0, 100).forEach(i -> {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("the runnable " + i + " be serviced by " + Thread.currentThread() + " start");
                    try {
                        Thread.sleep(1_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("the runnable " + i + " be serviced by " + Thread.currentThread() + " finished");
                }
            });
        });
    }
}
