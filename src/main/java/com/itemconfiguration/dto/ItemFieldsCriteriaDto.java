package com.itemconfiguration.dto;

import com.itemconfiguration.service.savestrategy.SaveForAllStrategy;
import java.util.List;

public class ItemFieldsCriteriaDto {

    private SaveForAllStrategy saveForAllStrategy;
    private List<MultipleField> multipleFields;

    public ItemFieldsCriteriaDto() {
    }

    public List<MultipleField> getMultipleFields() {
        return multipleFields;
    }

    public void setMultipleFields(List<MultipleField> multipleFields) {
        this.multipleFields = multipleFields;
    }

    public SaveForAllStrategy getSaveForAllStrategy() {
        return saveForAllStrategy;
    }

    public void setSaveForAllStrategy(SaveForAllStrategy saveForAllStrategy) {
        this.saveForAllStrategy = saveForAllStrategy;
    }
}
