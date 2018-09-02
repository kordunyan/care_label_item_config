package com.itemconfiguration.service.deletestrategy.itemfieldconfig;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.itemconfiguration.dto.ItemCrudOperationsDto;
import com.itemconfiguration.dto.ItemWithItemFieldConfigDto;
import com.itemconfiguration.dto.SaveItemFieldConfigDto;
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
    public void delete(ItemCrudOperationsDto curdOperationsDto) throws SaveItemFieldConfigException {
        List<ItemFieldConfig> originalItemFieldConfigs = curdOperationsDto.getItemFieldConfigs();
        if(CollectionUtils.isEmpty(originalItemFieldConfigs)) {
            return;
        }
        List<ItemFieldConfig> itemFieldConfigsToDelete = new ArrayList<>();
        itemFieldConfigsToDelete.addAll(getFieldConfigToDelete(getItems(curdOperationsDto), originalItemFieldConfigs));
        itemFieldConfigService.deleteItemFieldConfigs(itemFieldConfigsToDelete);
    }

    protected List<ItemWithItemFieldConfigsMap> getItems(ItemCrudOperationsDto curdOperationsDto) throws SaveItemFieldConfigException {
        return itemService.getAllItemsWithFieldConfigMapByItemNumbers(curdOperationsDto.getItemNumbers());
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
