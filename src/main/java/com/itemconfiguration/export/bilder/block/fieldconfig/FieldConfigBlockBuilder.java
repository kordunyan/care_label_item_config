package com.itemconfiguration.export.bilder.block.fieldconfig;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.export.bilder.line.FieldConfigLineBuilder;
import com.itemconfiguration.export.bilder.line.StaticLines;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class FieldConfigBlockBuilder {

    private final FieldConfigLineBuilder fieldConfigLineBuilder;

    public FieldConfigBlockBuilder(FieldConfigLineBuilder fieldConfigLineBuilder) {
        this.fieldConfigLineBuilder = fieldConfigLineBuilder;
    }

    public String build(List<FieldConfig> fieldConfigs) {
        if (CollectionUtils.isEmpty(fieldConfigs)) {
            return StringUtils.EMPTY;
        }
        return fieldConfigs.stream()
                .map(fieldConfigLineBuilder::build)
                .collect(Collectors.joining(StaticLines.INSERT_VALUES_SEPARATOR));
    }
}
