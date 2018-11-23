package com.itemconfiguration.export;

import com.itemconfiguration.domain.DataType;
import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.dto.ExportFieldConfigsDto;
import com.itemconfiguration.export.bilder.FieldConfigExportBaseScreeptsBuilder;
import com.itemconfiguration.export.bilder.block.datatype.DataTypeBlockBuilder;
import com.itemconfiguration.export.bilder.block.fieldconfig.FieldConfigBlockBuilder;
import com.itemconfiguration.service.DataTypeService;
import com.itemconfiguration.service.FieldConfigService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class FieldConfigsExportBuilder {

    private final FieldConfigExportBaseScreeptsBuilder fieldConfigExportBaseScreeptsBuilder;
    private final FieldConfigBlockBuilder fieldConfigBlockBuilder;
    private final FieldConfigService fieldConfigService;
    private final DataTypeService dataTypeService;
    private final DataTypeBlockBuilder dataTypeBlockBuilder;

    public FieldConfigsExportBuilder(FieldConfigExportBaseScreeptsBuilder fieldConfigExportBaseScreeptsBuilder,
            FieldConfigBlockBuilder fieldConfigBlockBuilder, FieldConfigService fieldConfigService,
            DataTypeBlockBuilder dataTypeBlockBuilder, DataTypeService dataTypeService) {
        this.fieldConfigExportBaseScreeptsBuilder = fieldConfigExportBaseScreeptsBuilder;
        this.fieldConfigBlockBuilder = fieldConfigBlockBuilder;
        this.dataTypeBlockBuilder = dataTypeBlockBuilder;
        this.fieldConfigService = fieldConfigService;
        this.dataTypeService = dataTypeService;
    }

    public String export(ExportFieldConfigsDto dto) {
        List<FieldConfig> fieldConfigs = getFieldConfisg(dto);
        if (CollectionUtils.isEmpty(fieldConfigs)) {
            return StringUtils.EMPTY;
        }
        List<String> result = new ArrayList<>();
        result.add(fieldConfigExportBaseScreeptsBuilder.buildDeclarations());
        result.add(dataTypeBlockBuilder.build(getDataTypes(fieldConfigs)));
        result.add(fieldConfigExportBaseScreeptsBuilder.buildDataTypesInsertionScreept());
        result.add(fieldConfigBlockBuilder.build(fieldConfigs));
        result.add(fieldConfigExportBaseScreeptsBuilder.buildFieldConfigsInsertionScreept());
        return StringUtils.join(result, StringUtils.EMPTY);
    }

    public List<DataType> getDataTypes(List<FieldConfig> fieldConfigs) {
        List<String> dataTypeNames = fieldConfigs.stream()
                .map(FieldConfig::getType)
                .distinct()
                .collect(Collectors.toList());
        return dataTypeService.getByNames(dataTypeNames);
    }

    protected List<FieldConfig> getFieldConfisg(ExportFieldConfigsDto dto) {
        switch (dto.getFieldConfigExportType()) {
            case BY_NAMES:
                return fieldConfigService.getAllByNames(dto.getOptions());
            case BY_TYPES:
                return fieldConfigService.getAllByTypes(dto.getOptions());
            case BY_OWNERS:
                return fieldConfigService.getAllByOwners(dto.getOptions());
            case ALL:
                return fieldConfigService.getAll();
            default:
                throw new IllegalArgumentException(String.format("field config export type [%s] is not suported",
                        dto.getFieldConfigExportType()));
        }
    }
}
