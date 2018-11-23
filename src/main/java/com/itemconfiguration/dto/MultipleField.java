package com.itemconfiguration.dto;

import java.util.List;

public class MultipleField {

    private String fieldConfigName;
    private List<String> values;

    public String getFieldConfigName() {
        return fieldConfigName;
    }

    public void setFieldConfigName(String fieldConfigName) {
        this.fieldConfigName = fieldConfigName;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
