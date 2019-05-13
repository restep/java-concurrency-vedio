package com.restep.basic.ch06;

/**
 * @author restep
 * @date 2019/5/11
 */
public class Trace {
    private ThreadStackTrace threadStackTrace = new ThreadStackTrace();

    public void trace() {
        threadStackTrace.trace();
    }
}
