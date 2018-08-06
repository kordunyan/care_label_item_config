package com.itemconfiguration.export.bilder.line;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class StaticLineBuilder {

    public static String NEW_LINE = "\n\r";
    public static String WHITE_SPACE = " ";

    public static String getWithNewFieldsSetId() {
        return new StringBuilder()
                .append("WITH new_field_set_id AS (INSERT INTO field_set DEFAULT VALUES RETURNING id) SELECT id INTO field_set_id FROM new_field_set_id;").append(NEW_LINE)

                .toString();
    }

    public static String getBlockSeparator() {
        return NEW_LINE + NEW_LINE;
    }
}
