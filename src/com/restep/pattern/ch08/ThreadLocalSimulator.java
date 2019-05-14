package com.restep.pattern.ch08;

import java.util.HashMap;
import java.util.Map;

/**
 * @author restep
 * @date 2019/5/14
 */
public class ThreadLocalSimulator<T> {
    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            storage.put(Thread.currentThread(), t);
        }
    }

    public T get() {
        synchronized (this) {
            return null != storage.get(Thread.currentThread())
                    ? storage.get(Thread.currentThread()) : initialValue();
        }
    }

    protected T initialValue() {
        return null;
    }
}
