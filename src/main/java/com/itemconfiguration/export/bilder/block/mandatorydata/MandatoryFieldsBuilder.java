package com.itemconfiguration.export.bilder.block.mandatorydata;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.export.bilder.Table;
import com.itemconfiguration.export.bilder.line.MandatoryDataLineBuilder;
import com.itemconfiguration.export.bilder.line.StaticLines;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component("MandatoryFieldsBuilder")
public class MandatoryFieldsBuilder implements MandatoryDataBuilder {

    private MandatoryDataLineBuilder mandatoryDataLineBuilder;

    public MandatoryFieldsBuilder(MandatoryDataLineBuilder mandatoryDataLineBuilder) {
        this.mandatoryDataLineBuilder = mandatoryDataLineBuilder;
    }

    @Override
    public List<String> build(ItemFieldConfig itemFieldConfig) {
        if (CollectionUtils.isEmpty(itemFieldConfig.getMandatoryFields())) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        result.add(buildInsertToTable());
        result.add(buildMandatoryFieldsLines(itemFieldConfig.getMandatoryFields()));
        return result;
    }

    private String buildMandatoryFieldsLines(List<MandatoryField> mandatoryFields) {
        return mandatoryFields.stream()
                .map(MandatoryField::getFieldConfig)
                .map(mandatoryDataLineBuilder::buildMandatoryFieldLine)
                .collect(Collectors.joining(StaticLines.INSERT_VALUES_SEPARATOR)) + StaticLines.SEMICOLON;
    }

    private String buildInsertToTable() {
        return new StringBuilder()
                .append("INSERT INTO ")
                .append(Table.MANDATORY_FIELD).append(StaticLines.NEW_LINE)
                .append(mandatoryDataLineBuilder.buildMandatoryFieldsTableFieldNames())
                .append(" VALUES").append(StaticLines.NEW_LINE)
                .toString();
    }
}
