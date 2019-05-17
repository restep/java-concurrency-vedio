package com.restep.juc.ch01;

/**
 * @author restep
 * @date 2019/5/16
 */
public class GetLockException extends Exception {
    public GetLockException() {
        super();
    }

    public GetLockException(String message) {
        super(message);
    }
}
