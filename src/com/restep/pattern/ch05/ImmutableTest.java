package com.restep.pattern.ch05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author restep
 * @date 2019/5/14
 */
public class ImmutableTest {
    private final int AGE;
    private final String NAME;
    private final List<String> LIST;

    public ImmutableTest(int AGE, String name) {
        this.AGE = AGE;
        this.NAME = name;
        LIST = new ArrayList<>();
    }

    public int getAGE() {
        return AGE;
    }

    public String getNAME() {
        return NAME;
    }

    public List<String> getLIST() {
        return Collections.unmodifiableList(LIST);
    }
}
