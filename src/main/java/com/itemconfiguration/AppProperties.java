package com.itemconfiguration;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    public static final String KEY_ITEM_FIELD_CONFIG_FILE_NAME = "${item.field.config.file.name}";

}
