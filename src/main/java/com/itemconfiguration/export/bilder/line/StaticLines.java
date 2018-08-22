package com.itemconfiguration.export.bilder.line;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class StaticLines {

    public static String NEW_LINE = "\n\r";
    public static String WHITE_SPACE = " ";
    public static final String INSERT_VALUES_SEPARATOR = ",\n\r";

    public static String getBlockSeparator() {
        return NEW_LINE + NEW_LINE;
    }
}
