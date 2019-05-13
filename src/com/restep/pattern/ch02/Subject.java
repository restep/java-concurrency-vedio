package com.restep.pattern.ch02;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 *
 * @author restep
 * @date 2019/5/13
 */
public class Subject {
    private List<Observer> observerList = new ArrayList<>();

    private int state;

    public void attach(Observer observer) {
        observerList.add(observer);
    }

    public void notifyAllObserver() {
        observerList.stream().forEach(Observer::update);
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        if (state == this.state) {
            return;
        }

        this.state = state;
        notifyAllObserver();
    }
}
