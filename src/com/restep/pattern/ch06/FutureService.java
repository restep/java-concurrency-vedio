package com.restep.pattern.ch06;

import java.util.function.Consumer;

/**
 * @author restep
 * @date 2019/5/14
 */
public class FutureService {
    public <T> Future<T> submit(final FutureTask<T> task) {
        AsynFuture<T> asynFuture = new AsynFuture<>();

        new Thread() {
            @Override
            public void run() {
                T result = task.call();
                asynFuture.done(result);
            }
        }.start();

        return asynFuture;
    }

    /**
     *
     * @param task
     * @param consumer 回调
     * @param <T>
     * @return
     */
    public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer) {
        AsynFuture<T> asynFuture = new AsynFuture<>();

        new Thread() {
            @Override
            public void run() {
                T result = task.call();
                asynFuture.done(result);
                consumer.accept(result);
            }
        }.start();

        return asynFuture;
    }
}
