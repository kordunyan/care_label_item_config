package com.itemconfiguration.export.bilder.block.itemfieldconfig;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;

import java.util.List;

public interface ItemFieldConfigLinesBuilder {
    List<String> buildLines(List<ItemFieldConfig> itemFieldConfigs, Item item);
}
