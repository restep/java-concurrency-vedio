package com.restep.pattern.ch13;

import java.io.*;
import java.net.Socket;

/**
 * @author restep
 * @date 2019/5/14
 */
public class ClientHandler implements Runnable {
    private final Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter printWriter = new PrintWriter(outputStream)) {
            while (running) {
                String message = br.readLine();
                if (null == message) {
                    break;
                }

                System.out.println("come form client " + message);
                printWriter.write("echo " + message + "\n");
                printWriter.flush();
            }
        } catch (IOException e) {
            this.running = false;
        } finally {
            this.stop();
        }

    }

    public void stop() {
        if (!running) {
            return;
        }

        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
