package com.itemconfiguration.dto;

import com.itemconfiguration.service.savestrategy.SaveForAllStrategy;
import java.util.List;

public class ItemFieldsCriteriaDto {

    private SaveForAllStrategy saveForAllStrategy;
    private List<MultipleField> multipleFields;
    private boolean withBatchType;
    private boolean ipps;
    private boolean sb;

    public ItemFieldsCriteriaDto() {
    }

    public List<MultipleField> getMultipleFields() {
        return multipleFields;
    }

    public boolean isWithBatchType() {
        return withBatchType;
    }

    public void setWithBatchType(boolean withBatchType) {
        this.withBatchType = withBatchType;
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
