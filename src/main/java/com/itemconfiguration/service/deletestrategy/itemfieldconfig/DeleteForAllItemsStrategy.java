package com.itemconfiguration.service.deletestrategy.itemfieldconfig;

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
import org.springframework.stereotype.Component;

@Component("DeleteForAllItemsStrategy")
public class DeleteForAllItemsStrategy implements DeleteItemFieldConfigStrategy{

    private ItemFieldConfigService itemFieldConfigService;
    private ItemService itemService;

    public DeleteForAllItemsStrategy(ItemFieldConfigService itemFieldConfigService, ItemService itemService) {
        this.itemFieldConfigService = itemFieldConfigService;
        this.itemService = itemService;
    }

    @Override
    public void delete(ItemCrudOperationsDto dto) throws SaveItemFieldConfigException {
        List<ItemFieldConfig> originalItemFieldConfigs = dto.getItemFieldConfigs();
        if(CollectionUtils.isEmpty(originalItemFieldConfigs)) {
            return;
        }
        List<ItemFieldConfig> itemFieldConfigsToDelete = new ArrayList<>();
        itemFieldConfigsToDelete.addAll(getFieldConfigToDelete(getItems(dto), originalItemFieldConfigs));
        itemFieldConfigService.deleteItemFieldConfigs(itemFieldConfigsToDelete);
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

    private List<ItemFieldConfig> getFieldConfigToDelete(List<ItemWithItemFieldConfigsMap> items, List<ItemFieldConfig> originalItemFieldConfigs) {
        List<ItemFieldConfig> result = new ArrayList<>();
        for (ItemWithItemFieldConfigsMap item : items) {
            for (ItemFieldConfig originalItemFieldConfig : originalItemFieldConfigs) {
                ItemFieldConfig itemFieldConfig = item.getItemFieldConfig(originalItemFieldConfig.getFieldConfig().getName());
                if (itemFieldConfig != null) {
                    item.deleteItemFieldConfig(itemFieldConfig);
                    result.add(itemFieldConfig);
                }
            }
        }
        return result;
    }
}
