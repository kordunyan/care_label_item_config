package com.itemconfiguration.dto;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;

import java.util.List;

public class ExportDataDto {
    private List<Item> items;

    private FieldConfigsWrapper fieldConfigsWrapper;

    public ExportDataDto(List<Item> items, FieldConfigsWrapper fieldConfigsWrapper) {
        this.items = items;
        this.fieldConfigsWrapper = fieldConfigsWrapper;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public FieldConfigsWrapper getFieldConfigsWrapper() {
        return fieldConfigsWrapper;
    }

    public void setFieldConfigsWrapper(FieldConfigsWrapper fieldConfigsWrapper) {
        this.fieldConfigsWrapper = fieldConfigsWrapper;
    }
}
