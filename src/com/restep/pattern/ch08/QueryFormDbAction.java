package com.restep.pattern.ch08;

/**
 * @author restep
 * @date 2019/5/14
 */
public class QueryFormDbAction {
    public void execute() {
        try {
            Thread.sleep(1_000L);

            String name = "db: " + Thread.currentThread().getName();
            ActionContext.getInstance().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
