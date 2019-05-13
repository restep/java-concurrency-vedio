package com.restep.pattern.ch04;

/**
 * @author restep
 * @date 2019/5/13
 */
public class Data {
    private final char[] buffer;

    private final ReadWriteLock READ_WRITE_LOCK = new ReadWriteLock();

    public Data(int size) {
        this.buffer = new char[size];

        for (int i = 0; i < size; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            READ_WRITE_LOCK.readLock();

            return doRead();
        } finally {
            READ_WRITE_LOCK.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            READ_WRITE_LOCK.writeLock();

            doWrite(c);
        } finally {
            READ_WRITE_LOCK.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;

            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private char[] doRead() {
        char[] newBuffer = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuffer[i] = buffer[i];
        }

        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return newBuffer;
    }
}
