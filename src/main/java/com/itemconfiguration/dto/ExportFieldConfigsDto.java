package com.itemconfiguration.dto;

import com.itemconfiguration.export.FieldConfigExportType;

import java.util.List;

public class ExportFieldConfigsDto {

    private FieldConfigExportType fieldConfigExportType;
    private List<String> options;

    public ExportFieldConfigsDto() {
    }

    public FieldConfigExportType getFieldConfigExportType() {
        return fieldConfigExportType;
    }

    public void setFieldConfigExportType(FieldConfigExportType fieldConfigExportType) {
        this.fieldConfigExportType = fieldConfigExportType;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
