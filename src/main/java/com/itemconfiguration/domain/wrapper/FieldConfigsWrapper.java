package com.itemconfiguration.domain.wrapper;

import com.itemconfiguration.domain.FieldConfig;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FieldConfigsWrapper {
    private List<FieldConfig> allFieldConfigs;
    private List<FieldConfig> fieldConfigs = new ArrayList<>();
    private List<FieldConfig> multipleFieldConfigs = new ArrayList<>();
    private Set<String> multipleFields;

    public FieldConfigsWrapper(List<FieldConfig> allFieldConfigs, Set<String> multipleFields) {
        this.allFieldConfigs = allFieldConfigs;
        this.multipleFields = multipleFields;
        initFieldConfigs();
    }

    private void initFieldConfigs() {
        if (CollectionUtils.isEmpty(allFieldConfigs)) {
            return;
        }
        allFieldConfigs.forEach(fieldConfig -> {
            if (multipleFields.contains(fieldConfig.getName())) {
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
