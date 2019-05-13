package com.restep.pattern.ch02;

import java.util.List;

/**
 * @author restep
 * @date 2019/5/13
 */
public class ThreadLifeCycleListener implements LifeCycleListener {
    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> idList) {
        if (null == idList && idList.isEmpty()) {
            return;
        }

        idList.stream().forEach(id -> new Thread(new ObserverRunnable(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("query for the id: " + id);

                    Thread.sleep(1_000L);
                    //int x = 1 / 0;

                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }, id).start());
    }

    @Override
    public void onEvent(ObserverRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("the runnable [" + event.getThread().getName() +
                    "] data changed and state is [" + event.getRunnableState() + "]");

            if (null != event.getThrowable()) {
                System.out.println("the runnable [" + event.getThread().getName() + "] process failed");
                event.getThrowable().printStackTrace();
            }
        }
    }
}
