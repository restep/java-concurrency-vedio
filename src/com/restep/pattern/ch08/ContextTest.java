package com.restep.pattern.ch08;

import java.util.stream.IntStream;

/**
 * @author restep
 * @date 2019/5/14
 */
public class ContextTest {
    public static void main(String[] args) {
        final ExecuteTask executeTask = new ExecuteTask();

        IntStream.range(1, 5).forEach(i -> {
            new Thread(new ExecuteTask()).start();
        });
    }
}
