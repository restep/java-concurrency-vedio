package com.restep.basic.ch06;

import java.util.Arrays;
import java.util.Optional;

/**
 * 线程追踪
 *
 * @author restep
 * @date 2019/5/11
 */
public class ThreadStackTrace {
    public void trace() {
        Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter(e -> !e.isNativeMethod())
                .forEach(e -> Optional.of(e.getClassName() + ":" + e.getMethodName() + ":" + e.getLineNumber())
                        .ifPresent(System.out::println));
    }
}
