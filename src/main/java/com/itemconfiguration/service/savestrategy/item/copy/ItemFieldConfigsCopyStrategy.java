package com.itemconfiguration.service.savestrategy.item.copy;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;

import java.util.List;

public interface ItemFieldConfigsCopyStrategy {
    void copy(List<ItemFieldConfig> itemFieldConfigsToCopy, Item owner);
}
