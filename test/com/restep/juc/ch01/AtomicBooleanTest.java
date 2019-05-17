package com.restep.juc.ch01;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author restep
 * @date 2019/5/16
 */
public class AtomicBooleanTest {
    @Test
    public void testCreate() {
        AtomicBoolean flag = new AtomicBoolean();
        assertFalse(flag.get());
    }

    @Test
    public void testCreate2() {
        AtomicBoolean flag = new AtomicBoolean(true);
        assertTrue(flag.get());
    }

    @Test
    public void testGetAndSet() {
        AtomicBoolean flag = new AtomicBoolean(true);
        boolean result = flag.getAndSet(false);
        assertTrue(result);
        assertFalse(flag.get());
    }

    @Test
    public void testCampareAndSet() {
        AtomicBoolean flag = new AtomicBoolean(true);
        boolean result = flag.compareAndSet(true, false);
        assertTrue(result);
        assertFalse(flag.get());
    }
}
