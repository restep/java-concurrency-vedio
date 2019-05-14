package com.restep.pattern.ch15;

/**
 * 接受异步消息的主动对象
 * @author restep
 * @date 2019/5/14
 */
public interface ActiveObject {

    Result makeString(int count, char fillChar);

    void displayString(String text);
}
