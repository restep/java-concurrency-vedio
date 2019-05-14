package com.restep.pattern.ch15;

/**
 * 对应ActiveObject的每一个方法
 *
 * @author restep
 * @date 2019/5/14
 */
public abstract class MethodRequest {

    protected final Servant servant;

    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}