package com.itemconfiguration.dto;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.service.savestrategy.SaveForAllStrategy;

import java.util.ArrayList;
import java.util.List;

public class SaveMandatoryDataDto {
    private ItemFieldConfig itemFieldConfig;
    private List<MandatoryTranslation> newMandatoryTrnaslations;
    private boolean saveForAll;
    private SaveForAllStrategy saveForAllStrategy;
    private List<String> itemNumbers = new ArrayList<>();

    public SaveMandatoryDataDto() {
    }

    public ItemFieldConfig getItemFieldConfig() {
        return itemFieldConfig;
    }

    public void setItemFieldConfig(ItemFieldConfig itemFieldConfig) {
        this.itemFieldConfig = itemFieldConfig;
    }

    public List<MandatoryTranslation> getNewMandatoryTrnaslations() {
        return newMandatoryTrnaslations;
    }

    public void setNewMandatoryTrnaslations(List<MandatoryTranslation> newMandatoryTrnaslations) {
        this.newMandatoryTrnaslations = newMandatoryTrnaslations;
    }

    public boolean isSaveForAll() {
        return saveForAll;
    }

    public void setSaveForAll(boolean saveForAll) {
        this.saveForAll = saveForAll;
    }

    public SaveForAllStrategy getSaveForAllStrategy() {
        return saveForAllStrategy;
    }

    public void setSaveForAllStrategy(SaveForAllStrategy saveForAllStrategy) {
        this.saveForAllStrategy = saveForAllStrategy;
    }

    public List<String> getItemNumbers() {
        return itemNumbers;
    }

    public void setItemNumbers(List<String> itemNumbers) {
        this.itemNumbers = itemNumbers;
    }
}
