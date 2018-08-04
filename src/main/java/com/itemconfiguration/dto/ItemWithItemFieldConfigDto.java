package com.itemconfiguration.dto;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;

import java.util.List;

public class ItemWithItemFieldConfigDto {
    private Item item;

    private List<ItemFieldConfig> itemFieldConfigs;

    public ItemWithItemFieldConfigDto() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<ItemFieldConfig> getItemFieldConfigs() {
        return itemFieldConfigs;
    }

    public void setItemFieldConfigs(List<ItemFieldConfig> itemFieldConfigs) {
        this.itemFieldConfigs = itemFieldConfigs;
    }
}
