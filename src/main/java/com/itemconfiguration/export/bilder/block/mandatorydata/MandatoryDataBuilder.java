package com.itemconfiguration.export.bilder.block.mandatorydata;

import com.itemconfiguration.domain.ItemFieldConfig;

import java.util.List;

public interface MandatoryDataBuilder {
    List<String> build(ItemFieldConfig itemFieldConfig);
}
