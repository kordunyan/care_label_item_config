package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.itemconfiguration.dto.ItemCrudOperationsDto;
import com.itemconfiguration.dto.MultipleField;
import com.itemconfiguration.exception.SaveItemFieldConfigException;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.ItemService;
import com.itemconfiguration.utils.ItemUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public abstract class AbstractItemFieldConfigSaveForAllStrategy implements ItemFieldConfigSaveStrategy {

    private ItemService itemService;
    private ItemFieldConfigService itemFieldConfigService;

    public AbstractItemFieldConfigSaveForAllStrategy(ItemService itemService,
            ItemFieldConfigService itemFieldConfigService) {
        this.itemService = itemService;
        this.itemFieldConfigService = itemFieldConfigService;
    }

    @Override
    public void save(ItemCrudOperationsDto dto) throws SaveItemFieldConfigException {
        List<ItemFieldConfig> changedItemFieldsConfigs = dto.getItemFieldConfigs();
        if (CollectionUtils.isEmpty(changedItemFieldsConfigs)) {
            return;
        }
        Item originalItem = dto.getItem();
        List<ItemFieldConfig> itemFieldConfigsForSave = new ArrayList<>();
        itemFieldConfigsForSave.addAll(setOriginalItemForChangedFieldConfigs(originalItem, changedItemFieldsConfigs));
        itemFieldConfigsForSave.addAll(setItemForChangedFieldConfigs(getItems(dto), changedItemFieldsConfigs, originalItem));
        itemFieldConfigService.saveAll(itemFieldConfigsForSave);
    }

    private List<ItemFieldConfig> setOriginalItemForChangedFieldConfigs(Item item, List<ItemFieldConfig> itemFieldConfigs) {
        return itemFieldConfigs.stream()
                .peek(itemFieldConfig -> itemFieldConfig.setItem(item))
                .collect(Collectors.toList());
    }

    protected List<ItemWithItemFieldConfigsMap> getItems(ItemCrudOperationsDto dto) throws SaveItemFieldConfigException {
        List<Item> items = itemService.findAllByItemNumbers(dto.getItemNumbers());
        List<MultipleField> multipleFields = dto.getItemFieldsCriteria().getMultipleFields();
        if (CollectionUtils.isEmpty(multipleFields)) {
            return ItemUtils.convertToItemWithItemFieldConfigsMap(items);
        }
        return items.stream()
                .filter(item -> ItemUtils.containsAllMultipleFields(item, multipleFields))
                .map(ItemWithItemFieldConfigsMap::new)
                .collect(Collectors.toList());
    }

    private List<ItemFieldConfig> setItemForChangedFieldConfigs(List<ItemWithItemFieldConfigsMap> items,
            List<ItemFieldConfig> changedItemFieldsConfigs, Item originalItem) {
        List<ItemFieldConfig> result = new ArrayList<>();

        items.stream()
                .filter(item -> !item.getItem().getId().equals(originalItem.getId()))
                .forEach(item -> {
                    for (ItemFieldConfig changedItemFieldConfig : changedItemFieldsConfigs) {
                        addChangedFieldConfig(result, item, changedItemFieldConfig);
                    }
                });
        return result;
    }

    protected abstract void addChangedFieldConfig(List<ItemFieldConfig> result, ItemWithItemFieldConfigsMap item, ItemFieldConfig changedItemFieldConfig);
}
