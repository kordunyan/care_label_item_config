package com.itemconfiguration.domain;

import java.util.Arrays;
import java.util.List;

public enum BatchBuilderType {
    IPPS,
    SB;

    public static List<BatchBuilderType> getAll() {
        return Arrays.asList(values());
    }
}
