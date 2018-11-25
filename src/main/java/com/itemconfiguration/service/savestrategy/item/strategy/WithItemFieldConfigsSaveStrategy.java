package com.itemconfiguration.service.savestrategy.item.strategy;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.dto.CopyItemDto;
import com.itemconfiguration.service.ItemService;
import com.itemconfiguration.service.RboPropertiesService;
import com.itemconfiguration.service.savestrategy.item.copy.ItemFieldConfigsCopyStrategy;
import com.itemconfiguration.utils.ItemUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WithItemFieldConfigsSaveStrategy implements ItemSaveStrategy {

    private final ItemFieldConfigsCopyStrategy itemFieldConfigsCopyStrategy;
    private final RboPropertiesService rboPropertiesService;
    private final ItemService itemService;


    public WithItemFieldConfigsSaveStrategy(ItemService itemService, ItemFieldConfigsCopyStrategy itemFieldConfigsCopyStrategy,
            RboPropertiesService rboPropertiesService) {
        this.itemFieldConfigsCopyStrategy = itemFieldConfigsCopyStrategy;
        this.rboPropertiesService = rboPropertiesService;
        this.itemService = itemService;
    }

    @Override
    public void save(CopyItemDto dto) {
        if (CollectionUtils.isEmpty(dto.getItems())) {
            return;
        }
        if (dto.getCopyFieldsStrategy() == CopyFieldsStrategy.FROM_COPY_ITEM || dto.getItems().size() == 1) {
            copyFromItemById(dto);
        } else {
            copyFromSuitableItem(dto);
        }
        itemService.saveAll(dto.getItems());
    }

    private void copyFromSuitableItem(CopyItemDto dto) {
        List<ItemWithFieldsMap> itemsToCopy = getItemsToCopy(dto);
        if (CollectionUtils.isEmpty(itemsToCopy)) {
            throw new IllegalStateException("There are no items to copy with item number: " + dto.getCopyItemNumber());
        }
        Item itemToCopyById = findItemToCopyById(itemsToCopy, dto.getCopyItemId());
        List<String> multipleFields = getRevesetMultipleFields();
        for (Item newItem : dto.getItems()) {
            Item suitableItem = findSuitableItem(itemsToCopy, ItemUtils.convertToItemWithFieldsMap(newItem), multipleFields, itemToCopyById);
            copyItemFieldConfigs(suitableItem, newItem);
        }
    }

    private List<String> getRevesetMultipleFields() {
        List<String> result = new LinkedList<>(rboPropertiesService.getMultipleFields());
        Collections.reverse(result);
        return result;
    }

    private Item findSuitableItem(List<ItemWithFieldsMap> itemsToCopy, ItemWithFieldsMap newItem, List<String> multipleFields, Item copyItemById) {
        if (itemsToCopy.size() == 1) {
            return itemsToCopy.get(0).getItem();
        }
        Item result = copyItemById;
        int suitableItemPriority = -1;

        for (ItemWithFieldsMap itemToCopy : itemsToCopy) {
            int currentPrioroty = 0;
            int fieldPrioroty = 1;
            for (String fieldName : multipleFields) {
                if (itemToCopy.hasField(fieldName) && newItem.hasField(fieldName)) {
                    if (itemToCopy.getFieldValue(fieldName).equalsIgnoreCase(newItem.getFieldValue(fieldName))) {
                        currentPrioroty += Math.pow(10, fieldPrioroty);
                    }
                }
                fieldPrioroty++;
            }
            if (currentPrioroty == 0) {
                continue;
            }
            if (suitableItemPriority < currentPrioroty) {
                suitableItemPriority = currentPrioroty;
                result = itemToCopy.getItem();
            }
        }
        return result;
    }

    private Item findItemToCopyById(List<ItemWithFieldsMap> itemsToCopy, Long copyItemId) {
        return itemsToCopy.stream()
                .map(ItemWithFieldsMap::getItem)
                .filter(item -> item.getId().equals(copyItemId))
                .findFirst()
                .orElse(null);
    }

    private void copyFromItemById(CopyItemDto dto) {
        itemService.getById(dto.getCopyItemId())
            .ifPresent(copyItem -> {
                dto.getItems().forEach(newItem -> copyItemFieldConfigs(copyItem, newItem));
            });
    }

    private void copyItemFieldConfigs(Item copyItem, Item newItem) {
        itemFieldConfigsCopyStrategy.copy(copyItem.getItemFieldConfigs(), newItem);
    }

    private List<ItemWithFieldsMap> getItemsToCopy(CopyItemDto dto) {
        return ItemUtils.convertToItemsWithFieldsMap(itemService.findByItemNumber(dto.getCopyItemNumber()));
    }
}
