package com.restep.pattern.ch02;

/**
 * @author restep
 * @date 2019/5/13
 */
public interface LifeCycleListener {
    void onEvent(ObserverRunnable.RunnableEvent event);
}
