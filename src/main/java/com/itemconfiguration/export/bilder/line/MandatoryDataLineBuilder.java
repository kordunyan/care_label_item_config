package com.itemconfiguration.export.bilder.line;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.export.bilder.BuilderUtils;
import org.springframework.stereotype.Component;

@Component
public class MandatoryDataLineBuilder {

    private static final String MANDATORY_FIELD_TABLE_NAME = "mandatory_field";
    private static final String MANDATORY_TRANSLATION_TABLE_NAME = "mandatory_translation";

    private static final String INSERT_LINE_FORAMAT = "(%-10s%-40s)";

    private static final String ITEM_FIELD_CONFIG_ID = "item_field_config_id";
    private static final String LANGUAGE_CODE = "language_code";
    private static final String FIELD_CONFIG_NAME = "field_config_name";


    public String buildMandatoryFieldsTableFieldsName() {
        return String.format(INSERT_LINE_FORAMAT, ITEM_FIELD_CONFIG_ID, FIELD_CONFIG_NAME);
    }

    public String mandatoryFieldLine(FieldConfig fieldConfig) {
        return String.format(INSERT_LINE_FORAMAT,
                BuilderUtils.escapeWithComma(ItemFieldConfigLineBuilder.VAR_ITEM_FIELD_CONFIG_ID),
                BuilderUtils.escapeValue(fieldConfig.getName()));
    }


}
