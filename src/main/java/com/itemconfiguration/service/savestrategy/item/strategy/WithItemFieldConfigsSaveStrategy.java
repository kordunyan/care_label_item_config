package com.itemconfiguration.service.savestrategy.item.strategy;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.service.ItemService;
import com.itemconfiguration.service.RboPropertiesService;
import com.itemconfiguration.service.savestrategy.item.copy.ItemFieldConfigsCopyStrategy;

public class WithItemFieldConfigsSaveStrategy extends DefaultItemSaveStrategy {

    private final ItemFieldConfigsCopyStrategy itemFieldConfigsCopyStrategy;

    public WithItemFieldConfigsSaveStrategy(ItemService itemService, ItemFieldConfigsCopyStrategy itemFieldConfigsCopyStrategy,
            RboPropertiesService rboPropertiesService) {
        super(rboPropertiesService, itemService);
        this.itemFieldConfigsCopyStrategy = itemFieldConfigsCopyStrategy;
    }

    protected void copyFromItem(Item newItem, Item copyItem) {
        super.copyFromItem(newItem, copyItem);
        copyItemFieldConfigs(copyItem, newItem);
    }

    private void copyItemFieldConfigs(Item copyItem, Item newItem) {
        itemFieldConfigsCopyStrategy.copy(copyItem.getItemFieldConfigs(), newItem);
    }

}
