package com.restep.pattern.ch02;

/**
 * 八进制
 *
 * @author restep
 * @date 2019/5/13
 */
public class OctalObserver extends Observer {
    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}
