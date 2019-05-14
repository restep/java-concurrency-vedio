package com.restep.pattern.ch08;

/**
 * @author restep
 * @date 2019/5/14
 */
public class ExecuteTask implements Runnable {
    private QueryFormDbAction dbAction = new QueryFormDbAction();
    private QueryFormHttpAction httpAction = new QueryFormHttpAction();

    @Override
    public void run() {
        Context context = ActionContext.getInstance().getContext();
        dbAction.execute();
        System.out.println("dbQuery success");

        httpAction.execute();
        System.out.println("httpQuery success");

        System.out.println("name-> " + context.getName() + " cardId-> " + context.getCardId());
    }
}
