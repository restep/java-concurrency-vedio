package com.restep.pattern.ch02;

/**
 * 二进制
 *
 * @author restep
 * @date 2019/5/13
 */
public class BinaryObserver extends Observer {
    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}
