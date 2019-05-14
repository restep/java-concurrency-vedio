package com.restep.pattern.ch12;

import java.util.stream.IntStream;

/**
 * Thread-Per-Message
 * @author restep
 * @date 2019/5/14
 */
public class MessageHandlerTest {
    public static void main(String[] args) {
        MessageHandler handler = new MessageHandler();

        IntStream.rangeClosed(0, 10)
                .forEach(i -> handler.request(new Message(String.valueOf(i))));

        handler.shutdown();
    }
}
