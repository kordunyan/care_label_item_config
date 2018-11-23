package com.itemconfiguration.export.bilder.line;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.export.bilder.BuilderUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class FieldConfigLineBuilder {

    public String build(FieldConfig fieldConfig) {
        if (fieldConfig == null) {
            return StringUtils.EMPTY;
        }
        return String.format("(%s,%s,%s,%s)",
                BuilderUtils.escapeValue(fieldConfig.getName()),
                BuilderUtils.escapeValue(fieldConfig.getType()),
                BuilderUtils.escapeValue(fieldConfig.getOwner()),
                fieldConfig.isPrintable());
    }

}
