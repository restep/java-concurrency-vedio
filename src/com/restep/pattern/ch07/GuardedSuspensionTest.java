package com.restep.pattern.ch07;

/**
 * TODO 程序有问题
 * @author restep
 * @date 2019/5/14
 */
public class GuardedSuspensionTest {
    public static void main(String[] args) {
        final RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "ReStep").start();

        ServerThread serverThread = new ServerThread(requestQueue);
        serverThread.start();
        try {
            serverThread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serverThread.close();

    }
}
