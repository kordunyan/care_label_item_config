package com.itemconfiguration.export.bilder.block.mandatorydata;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.export.bilder.Table;
import com.itemconfiguration.export.bilder.line.MandatoryDataLineBuilder;
import com.itemconfiguration.export.bilder.line.StaticLines;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component("MandatoryTranslationsBuilder")
public class MandatoryTranslationsBuilder implements MandatoryDataBuilder {

    private MandatoryDataLineBuilder mandatoryDataLineBuilder;

    public MandatoryTranslationsBuilder(MandatoryDataLineBuilder mandatoryDataLineBuilder) {
        this.mandatoryDataLineBuilder = mandatoryDataLineBuilder;
    }

    @Override
    public List<String> build(ItemFieldConfig itemFieldConfig) {
        if (CollectionUtils.isEmpty(itemFieldConfig.getMandatoryTranslations())) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        result.add(buildInsertToTable());
        result.add(buildMandatoryTranslationLines(itemFieldConfig.getMandatoryTranslations()));
        return result;
    }

    private String buildMandatoryTranslationLines(List<MandatoryTranslation> mandatoryTranslations) {
        return mandatoryTranslations.stream()
                .map(MandatoryTranslation::getLanguage)
                .map(mandatoryDataLineBuilder::buildMandatoryTranslationLine)
                .collect(Collectors.joining(StaticLines.INSERT_VALUES_SEPARATOR)) + StaticLines.SEMICOLON;
    }

    private String buildInsertToTable() {
        return new StringBuilder()
                .append("INSERT INTO ")
                .append(Table.MANDATORY_TRANSLATION).append(StaticLines.NEW_LINE)
                .append(mandatoryDataLineBuilder.buildMandatoryTranslationsTableFieldNames())
                .append(" VALUES").append(StaticLines.NEW_LINE)
                .toString();
    }
}
