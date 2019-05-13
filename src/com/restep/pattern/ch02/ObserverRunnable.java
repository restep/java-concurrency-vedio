package com.restep.pattern.ch02;

/**
 * @author restep
 * @date 2019/5/13
 */
public abstract class ObserverRunnable implements Runnable {
    protected final LifeCycleListener lifeCycleListener;

    public ObserverRunnable(final LifeCycleListener lifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener;
    }

    protected void notifyChange(final RunnableEvent runnableEvent) {
        lifeCycleListener.onEvent(runnableEvent);
    }

    public static class RunnableEvent {
        private final RunnableState runnableState;

        private final Thread thread;

        private final Throwable throwable;

        public RunnableEvent(RunnableState runnableState, Thread thread, Throwable throwable) {
            this.runnableState = runnableState;
            this.thread = thread;
            this.throwable = throwable;
        }

        public RunnableState getRunnableState() {
            return runnableState;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }

    public enum RunnableState {
        RUNNING,
        ERROR,
        DONE
    }
}
