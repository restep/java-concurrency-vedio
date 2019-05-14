package com.restep.pattern.ch08;

/**
 * 单例设计模式实现 ActionContext
 *
 * @author restep
 * @date 2019/5/14
 */
public class ActionContext {
    private static final ThreadLocal<Context> THREAD_LOCAL = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private ActionContext() {

    }

    public Context getContext() {
        return THREAD_LOCAL.get();
    }

    private static class ContextHolder {
        private static final ActionContext ACTION_CONTEXT = new ActionContext();
    }

    public static ActionContext getInstance() {
        return ContextHolder.ACTION_CONTEXT;
    }
}
