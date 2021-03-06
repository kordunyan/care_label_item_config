package com.itemconfiguration.domain;

import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;

import java.util.List;

public class ExportData {
    private List<Item> items;
    private List<String> itemNumbers;

    private FieldConfigsWrapper fieldConfigsWrapper;

    public ExportData(List<Item> items, FieldConfigsWrapper fieldConfigsWrapper) {
        this.items = items;
        this.fieldConfigsWrapper = fieldConfigsWrapper;
    }

    public ExportData(List<Item> items, FieldConfigsWrapper fieldConfigsWrapper, List<String> itemNumbers) {
        this.items = items;
        this.itemNumbers = itemNumbers;
        this.fieldConfigsWrapper = fieldConfigsWrapper;
    }

    public List<String> getItemNumbers() {
        return itemNumbers;
    }

    public void setItemNumbers(List<String> itemNumbers) {
        this.itemNumbers = itemNumbers;
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
