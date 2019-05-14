package com.restep.pattern.ch13;

import java.io.IOException;

/**
 * cmd --> telnet localhost 13345
 * @author restep
 * @date 2019/5/14
 */
public class ClientHandlerTest {
    public static void main(String[] args) {
        AppServer server = new AppServer(13345);
        server.start();

        try {
            Thread.sleep(15_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
