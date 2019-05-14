package com.restep.pattern.ch13;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author restep
 * @date 2019/5/14
 */
public class AppServer extends Thread {
    private final int port;

    private static final int DEFAULT_PORT = 12722;

    private volatile boolean start = true;

    private List<ClientHandler> clientHandlers = new ArrayList<>();

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private ServerSocket serverSocket;

    public AppServer() {
        this(DEFAULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(port);

            while (start) {
                Socket client = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executor.submit(clientHandler);

                this.clientHandlers.add(clientHandler);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.dispose();
        }
    }

    public void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        this.serverSocket.close();
    }

    private void dispose() {
        this.clientHandlers.stream().forEach(ClientHandler::stop);
        this.executor.shutdown();
    }
}
