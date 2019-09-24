package com.itemconfiguration.domain;

import java.util.Arrays;
import java.util.List;

public enum LabelType {

    BOOKLET(4), BOOKLET_REVERSE(4), SINGLE_SIDE(1), STRAIGHT_CUT(2);

    LabelType(Integer amountPanel) {
        this.amountPanel = amountPanel;
    }

    private Integer amountPanel;

    public static List<LabelType> getAll() {
        return Arrays.asList(values());
    }
}
