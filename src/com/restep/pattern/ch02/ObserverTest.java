package com.restep.pattern.ch02;

/**
 * @author restep
 * @date 2019/5/13
 */
public class ObserverTest {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);

        subject.setState(10);

        subject.setState(10);

        subject.setState(15);
    }
}
