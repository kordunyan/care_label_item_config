package com.itemconfiguration.dto;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.FieldSet;
import com.itemconfiguration.domain.Item;

import java.util.List;

public class ItemWithoutItemFieldConfigsDto {

    private Long id;
    private boolean ipps;
    private boolean sb;
    private FieldSet fieldSet;
    private List<Field> fields;

    public ItemWithoutItemFieldConfigsDto() {
    }

    public ItemWithoutItemFieldConfigsDto(Item item) {
        this.id = item.getId();
        this.ipps = item.isIpps();
        this.sb = item.isSb();
        this.fieldSet = item.getFieldSet();
        this.fields = item.getFields();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIpps() {
        return ipps;
    }

    public void setIpps(boolean ipps) {
        this.ipps = ipps;
    }

    public boolean isSb() {
        return sb;
    }

    public void setSb(boolean sb) {
        this.sb = sb;
    }

    public FieldSet getFieldSet() {
        return fieldSet;
    }

    public void setFieldSet(FieldSet fieldSet) {
        this.fieldSet = fieldSet;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
