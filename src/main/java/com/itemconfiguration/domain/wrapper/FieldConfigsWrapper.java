package com.itemconfiguration.domain.wrapper;

import com.itemconfiguration.domain.FieldConfig;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class FieldConfigsWrapper {
    private List<FieldConfig> allFieldConfigs;
    private List<FieldConfig> fieldConfigs = new ArrayList<>();
    private List<FieldConfig> multipleFieldConfigs = new ArrayList<>();

    public FieldConfigsWrapper(List<FieldConfig> allFieldConfigs) {
        this.allFieldConfigs = allFieldConfigs;
        initFieldConfigs();
    }

    private void initFieldConfigs() {
        if (CollectionUtils.isEmpty(allFieldConfigs)) {
            return;
        }
        allFieldConfigs.forEach(fieldConfig -> {
            if (fieldConfig.isMultiple()) {
                multipleFieldConfigs.add(fieldConfig);
            } else {
                fieldConfigs.add(fieldConfig);
            }
        });
    }

    public List<FieldConfig> getAllFieldConfigs() {
        return allFieldConfigs;
    }

    public List<FieldConfig> getFieldConfigs() {
        return fieldConfigs;
    }

    public List<FieldConfig> getMultipleFieldConfigs() {
        return multipleFieldConfigs;
    }
}
