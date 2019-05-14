package com.restep.pattern.ch09;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author restep
 * @date 2019/5/14
 */
public class BalkingData {
    private final String FILE_NAME;

    private String content;

    private boolean changed;

    public BalkingData() {
        FILE_NAME = null;
    }

    public BalkingData(String FILE_NAME, String content) {
        this.FILE_NAME = FILE_NAME;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent) {
        this.content = newContent;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }

        doSave();

        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls do save content=" + content);
        Writer writer = new FileWriter(FILE_NAME, true);
        writer.write(content);
        writer.write("\n");
        writer.flush();
        writer.close();
    }
}
