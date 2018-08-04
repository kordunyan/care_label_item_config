package com.itemconfiguration.service.savestrategy;

import com.itemconfiguration.domain.AppFields;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.itemconfiguration.dto.SaveItemFieldConfigDto;
import com.itemconfiguration.exception.SaveItemFieldConfigException;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.ItemService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractSaveForAllStrategy implements ItemFieldConfigSaveStrategy {

    private ItemService itemService;
    private ItemFieldConfigService itemFieldConfigService;

    public AbstractSaveForAllStrategy(ItemService itemService,
            ItemFieldConfigService itemFieldConfigService) {
        this.itemService = itemService;
        this.itemFieldConfigService = itemFieldConfigService;
    }

    @Override
    public void save(SaveItemFieldConfigDto saveItemFieldConfigDto) throws SaveItemFieldConfigException {
        List<ItemFieldConfig> changedItemFieldsConfigs = saveItemFieldConfigDto.getItemFieldConfigs();
        Item originalItem = saveItemFieldConfigDto.getItem();
        if (CollectionUtils.isEmpty(changedItemFieldsConfigs)) {
            return;
        }
        List<ItemFieldConfig> itemFieldConfigsForSave = new ArrayList<>();
        itemFieldConfigsForSave.addAll(getUpdatedOriginalItemFields(originalItem, changedItemFieldsConfigs));
        itemFieldConfigsForSave.addAll(getUpdatedItemsFields(getAllItemsByItemNumber(originalItem), changedItemFieldsConfigs));
        itemFieldConfigService.saveAll(itemFieldConfigsForSave);
    }

    private List<ItemFieldConfig> getUpdatedItemsFields(List<ItemWithItemFieldConfigsMap> items, List<ItemFieldConfig> changedItemFieldsConfigs) {
        List<ItemFieldConfig> result = new ArrayList<>();
        for (ItemWithItemFieldConfigsMap item : items) {
            for (ItemFieldConfig changedItemFieldConfig : changedItemFieldsConfigs) {
                addChangedFieldConfig(result, item, changedItemFieldConfig);
            }
        }
        return result;
    }

    private List<ItemWithItemFieldConfigsMap> getAllItemsByItemNumber(Item originalItem) throws SaveItemFieldConfigException {
        ItemWithFieldsMap originalItemWithFildsMap = new ItemWithFieldsMap(originalItem);
        if (!originalItemWithFildsMap.containsField(AppFields.D2COMM_ITEM_NUMBER)) {
            throw new SaveItemFieldConfigException(AppFields.D2COMM_ITEM_NUMBER + " field does not exist in item");
        }
        List<Item> items = itemService.findByItemNumber(originalItemWithFildsMap.getFieldValue(AppFields.D2COMM_ITEM_NUMBER));
        return items.stream()
                .filter(item -> !item.getId().equals(originalItem.getId()))
                .map(ItemWithItemFieldConfigsMap::new)
                .collect(Collectors.toList());
    }

    private List<ItemFieldConfig> getUpdatedOriginalItemFields(Item item, List<ItemFieldConfig> itemFieldConfigs) {
        return itemFieldConfigs
                .stream()
                .peek(itemFieldConfig -> itemFieldConfig.setItem(item))
                .collect(Collectors.toList());
    }

    protected abstract void addChangedFieldConfig(List<ItemFieldConfig> result, ItemWithItemFieldConfigsMap item, ItemFieldConfig changedItemFieldConfig);

}
