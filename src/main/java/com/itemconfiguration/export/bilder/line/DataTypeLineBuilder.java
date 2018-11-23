package com.itemconfiguration.export.bilder.line;

import com.itemconfiguration.domain.DataType;
import com.itemconfiguration.export.bilder.BuilderUtils;
import org.springframework.stereotype.Component;

@Component
public class DataTypeLineBuilder {

    public String build(DataType dataType) {
        return String.format("(%s,%s)",
                BuilderUtils.escapeValue(dataType.getName()),
                dataType.isInstruction());
    }

}
