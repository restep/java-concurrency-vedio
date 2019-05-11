package com.restep.ch07;

import java.util.ArrayList;
import java.util.Iterator;
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
public class SimpleThreadPool extends Thread {
    private int size;

    private final int QUEUE_SIZE;

    private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private static volatile int seq = 0;

    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private static final ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    private static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private static final List<WorkTask> THREAD_QUEUE = new ArrayList<>();

    private final DiscardPolicy discardPolicy;

    public static final DiscardPolicy DEFAULT_DISCARD_POLICY = new DiscardPolicy() {
        @Override
        public void discard() throws DiscardException {
            throw new DiscardException("discard this task");
        }
    };

    private volatile boolean destroy = false;

    private int min;

    private int max;

    private int active;

    public SimpleThreadPool() {
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.QUEUE_SIZE = queueSize;
        this.discardPolicy = discardPolicy;

        init();
    }

    private void init() {
        for (int i = 0; i < min; i++) {
            createWorkThread();
        }
        size = min;

        this.start();
    }

    public void submit(Runnable runnable) {
        if (destroy) {
            throw new IllegalStateException("the thread pool already destroy and not allow submit task");
        }

        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > QUEUE_SIZE) {
                discardPolicy.discard();
            }

            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    private void createWorkThread() {
        WorkTask task = new WorkTask(GROUP, THREAD_PREFIX + seq++);
        task.start();

        THREAD_QUEUE.add(task);
    }

    @Override
    public void run() {
        while (!destroy) {
            System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d\n",
                    this.min, this.active, this.max, this.size, TASK_QUEUE.size());
            try {
                Thread.sleep(5_000L);
                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createWorkThread();
                    }
                    System.out.println("the pool incremented to active");
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createWorkThread();
                    }
                    System.out.println("the pool incremented to max");
                    size = max;
                }

                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && size > active) {
                        System.out.println("=============Reduce=============");

                        int releaseSize = size - active;
                        for (Iterator<WorkTask> iterator = THREAD_QUEUE.iterator(); iterator.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }

                            WorkTask task = iterator.next();
                            task.close();
                            task.interrupt();
                            iterator.remove();

                            releaseSize--;
                        }

                        size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
                            System.out.println(">>>>>>>>>>>>>>closed<<<<<<<<<<<<<<<<");
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

    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    public void shutdown() {
        while (!TASK_QUEUE.isEmpty()) {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (THREAD_QUEUE) {
            int initValue = THREAD_QUEUE.size();
            while (initValue > 0) {
                for (WorkTask task : THREAD_QUEUE) {
                    if (TaskState.BLOCKED == task.getTaskState()) {
                        task.interrupt();
                        task.close();

                        initValue--;
                    } else {
                        try {
                            Thread.sleep(10L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        System.out.println("GROUP.activeCount(): " + GROUP.activeCount());

        this.destroy = true;
        System.out.println("the thread pool disposed");
    }

    /**
     * 拒绝策略
     */
    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    public int getSize() {
        return size;
    }

    public int getQUEUE_SIZE() {
        return QUEUE_SIZE;
    }

    public boolean hasDestory() {
        return this.destroy;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    public static void main(String[] args) {
        SimpleThreadPool threadPool = new SimpleThreadPool();

        IntStream.rangeClosed(0, 40).forEach(i -> {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("the runnable " + i + " be serviced by " + Thread.currentThread() + " start");
                    try {
                        Thread.sleep(3_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("the runnable " + i + " be serviced by " + Thread.currentThread() + " finished");
                }
            });
        });

        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();

        /*
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("========");
            }
        });
        */
    }
}
