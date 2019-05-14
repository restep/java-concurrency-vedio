package com.restep.pattern.ch08;

/**
 * @author restep
 * @date 2019/5/14
 */
public class QueryFormHttpAction {
    public void execute() {
        Context context = ActionContext.getInstance().getContext();
        String name = context.getName();
        String cardId = getCardId();
        context.setCardId(cardId);
    }

    private String getCardId() {
        try {
            Thread.sleep(1_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "id: " + Thread.currentThread().getId();
    }
}
