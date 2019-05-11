package com.restep.ch07;

/**
 * 拒绝策略
 *
 * @author restep
 * @date 2019/5/12
 */
public interface DiscardPolicy {
    void discard() throws DiscardException;
}
