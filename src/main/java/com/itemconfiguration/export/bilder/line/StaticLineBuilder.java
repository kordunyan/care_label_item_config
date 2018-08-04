package com.itemconfiguration.export.bilder.line;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class StaticLineBuilder {

    public static String NEW_LINE = "\n\r";
    public static String WHITE_SPACE = " ";


    public static String getBlockSeparator() {
        return NEW_LINE + NEW_LINE;
    }
}
