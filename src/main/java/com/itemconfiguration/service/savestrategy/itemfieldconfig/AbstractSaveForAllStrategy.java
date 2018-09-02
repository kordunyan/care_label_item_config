package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
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
        itemFieldConfigsForSave.addAll(getUpdatedItemsFields(getItemsForUpdate(saveItemFieldConfigDto), changedItemFieldsConfigs));
        itemFieldConfigService.saveAll(itemFieldConfigsForSave);
    }

    protected List<ItemWithItemFieldConfigsMap> getItemsForUpdate(SaveItemFieldConfigDto saveItemFieldConfigDto) throws SaveItemFieldConfigException {
        //return itemService.getAllItemsWithFieldConfigMapByItemNumbers(Arrays.asList("022008-W", "022008-W-UFO_HERING", "THG-77520-N", "THG-77520-O", "THG-77520-W", "THG-78173-N", "THG-78173-W"));
//        return itemService.getAllItemsWithFieldConfigMapByItemNumbers(Arrays.asList("032431-B",
//                "032431-W",
//                "032463-B",
//                "032463-B",
//                "032466-B",
//                "032466-W",
//                "032471-B",
//                "032471-W",
//                "032478-B",
//                "032478-W",
//                "032480-B",
//                "032480-W",
//                "17633-W",
//                "CKJ-73367-B",
//                "CKJ-73367-O",
//                "CKJ-73367-W",
//                "CKJ-74141-B",
//                "CKJ-74141-W",
//                "CKJ-74142-B",
//                "CKJ-74142-W",
//                "THG-71717-W",
//                "THG-71987-W",
//                "THG-72804-N",
//                "THG-72804-O",
//                "THG-72804-W",
//                "THG-73713-N",
//                "THG-73713-W",
//                "THG-74719-O"));
        return itemService.getAllItemsWithFieldConfigMapByItemNumber(saveItemFieldConfigDto.getItem());
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

    private List<ItemFieldConfig> getUpdatedOriginalItemFields(Item item, List<ItemFieldConfig> itemFieldConfigs) {
        return itemFieldConfigs
                .stream()
                .peek(itemFieldConfig -> itemFieldConfig.setItem(item))
                .collect(Collectors.toList());
    }

    protected abstract void addChangedFieldConfig(List<ItemFieldConfig> result, ItemWithItemFieldConfigsMap item, ItemFieldConfig changedItemFieldConfig);

}
