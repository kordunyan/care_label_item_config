package com.itemconfiguration.export.bilder.line;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.Language;
import com.itemconfiguration.export.bilder.BuilderUtils;
import org.springframework.stereotype.Component;

@Component
public class MandatoryDataLineBuilder {

    private static final String INSERT_LINE_FORAMAT = "(%-15s%-15s)";

    private static final String ITEM_FIELD_CONFIG_ID = "item_field_config_id,";
    private static final String LANGUAGE_CODE = "language_code";
    private static final String FIELD_CONFIG_NAME = "field_config_name";


    public String buildMandatoryFieldsTableFieldNames() {
        return String.format(INSERT_LINE_FORAMAT, ITEM_FIELD_CONFIG_ID, FIELD_CONFIG_NAME);
    }

    public String buildMandatoryTranslationsTableFieldNames() {
        return String.format(INSERT_LINE_FORAMAT, ITEM_FIELD_CONFIG_ID, LANGUAGE_CODE);
    }

    public String buildMandatoryFieldLine(FieldConfig fieldConfig) {
        return String.format(INSERT_LINE_FORAMAT,
                ItemFieldConfigLineBuilder.VAR_ITEM_FIELD_CONFIG_ID + ",",
                BuilderUtils.escapeValue(fieldConfig.getName()));
    }

    public String buildMandatoryTranslationLine(Language language) {
        return String.format(INSERT_LINE_FORAMAT,
                ItemFieldConfigLineBuilder.VAR_ITEM_FIELD_CONFIG_ID + ",",
                BuilderUtils.escapeValue(language.getCode()));
    }

}
