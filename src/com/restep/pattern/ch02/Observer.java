package com.restep.pattern.ch02;

/**
 * @author restep
 * @date 2019/5/13
 */
public abstract class Observer {
    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract void update();
}
