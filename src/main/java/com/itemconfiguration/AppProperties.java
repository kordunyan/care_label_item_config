package com.itemconfiguration;

import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    public static final String KEY_ITEM_FIELD_CONFIG_FILE_NAME = "${item.field.config.file.name}";
    public static final String KEY_FIELD_CONFIG_FILE_NAME = "${field.config.file.name}";

}
