package com.itemconfiguration.service.deletestrategy.itemfieldconfig;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.itemconfiguration.dto.ItemWithItemFieldConfigDto;
import com.itemconfiguration.exception.SaveItemFieldConfigException;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.ItemService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("forAll")
public class DeleteForAllItemsStrategy implements DeleteItemFieldConfigStrategy{

    private ItemFieldConfigService itemFieldConfigService;
    private ItemService itemService;

    public DeleteForAllItemsStrategy(ItemFieldConfigService itemFieldConfigService, ItemService itemService) {
        this.itemFieldConfigService = itemFieldConfigService;
        this.itemService = itemService;
    }

    @Override
    public void delete(ItemWithItemFieldConfigDto itemWithItemFieldConfigDto) throws SaveItemFieldConfigException {
        Item originalItem = itemWithItemFieldConfigDto.getItem();
        List<ItemFieldConfig> originalItemFieldConfigs = itemWithItemFieldConfigDto.getItemFieldConfigs();
        if(CollectionUtils.isEmpty(originalItemFieldConfigs)) {
            return;
        }
        List<ItemFieldConfig> itemFieldConfigsToDelete = new ArrayList<>();
        itemFieldConfigsToDelete.addAll(originalItemFieldConfigs);
        itemFieldConfigsToDelete.addAll(getFieldConfigToDelete(itemService.getAllItemsWithFieldConfigMapByItemNumber(originalItem),
                originalItemFieldConfigs));
        itemFieldConfigService.deleteItemFieldConfigs(itemFieldConfigsToDelete);
    }

    private List<ItemFieldConfig> getFieldConfigToDelete(List<ItemWithItemFieldConfigsMap> items, List<ItemFieldConfig> originalItemFieldConfigs) {
        List<ItemFieldConfig> result = new ArrayList<>();
        for (ItemWithItemFieldConfigsMap item : items) {
            for (ItemFieldConfig originalItemFieldConfig : originalItemFieldConfigs) {
                ItemFieldConfig itemFieldConfig = item.getItemFieldConfig(originalItemFieldConfig.getFieldConfigName());
                if (itemFieldConfig != null) {
                    itemFieldConfig.setItem(null);
                    result.add(itemFieldConfig);
                }
            }
        }
        return result;
    }
}
