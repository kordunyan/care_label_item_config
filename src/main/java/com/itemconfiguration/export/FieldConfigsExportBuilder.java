package com.itemconfiguration.export;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.dto.ExportFieldConfigsDto;
import com.itemconfiguration.service.FieldConfigService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FieldConfigsExportBuilder {

    private FieldConfigService fieldConfigService;

    public FieldConfigsExportBuilder(FieldConfigService fieldConfigService) {
        this.fieldConfigService = fieldConfigService;
    }

    public void export(ExportFieldConfigsDto dto) {
        List<FieldConfig> fieldConfigs = getFieldConfisg(dto);

        System.out.println(fieldConfigs.size());
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
