package com.restep.juc.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author restep
 * @date 2019/6/1
 */
public class AtomicBooleanTest {
    public void testCreate() {
        AtomicBoolean flag = new AtomicBoolean();
        System.out.println(flag.get());
    }

    public void testCreate2() {
        AtomicBoolean flag = new AtomicBoolean(true);
        System.out.println(flag.get());
    }

    public void testGetAndSet() {
        AtomicBoolean flag = new AtomicBoolean(true);
        boolean result = flag.getAndSet(false);
        System.out.println(result);
        System.out.println(flag.get());
    }

    public void testCampareAndSet() {
        AtomicBoolean flag = new AtomicBoolean(true);
        boolean result = flag.compareAndSet(true, false);
        System.out.println(result);
        System.out.println(flag.get());
    }
}
