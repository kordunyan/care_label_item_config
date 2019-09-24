package com.itemconfiguration.domain;

import java.util.Arrays;
import java.util.List;

public enum PrintType {
    ROTARY,
    ROTARY_STEPPING,
    THERMAL,
    PAD,
    LASER,
    LASER_DIE_CUT,
    LASER_PRE_DIE_CUT,
    SONIC_WELD;

    public static List<PrintType> getAll() {
        return Arrays.asList(values());
    }
}
