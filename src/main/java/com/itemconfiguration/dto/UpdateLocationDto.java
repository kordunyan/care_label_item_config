package com.itemconfiguration.dto;

import java.util.List;

public class UpdateLocationDto {
    private List<String> itemNumbers;
    private boolean ipps;
    private boolean sb;

    public UpdateLocationDto() {
    }

    public List<String> getItemNumbers() {
        return itemNumbers;
    }

    public void setItemNumbers(List<String> itemNumbers) {
        this.itemNumbers = itemNumbers;
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
}
