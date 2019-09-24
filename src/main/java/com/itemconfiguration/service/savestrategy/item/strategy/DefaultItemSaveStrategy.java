package com.itemconfiguration.service.savestrategy.item.strategy;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemPrintType;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.dto.CopyItemDto;
import com.itemconfiguration.service.ItemService;
import com.itemconfiguration.service.RboPropertiesService;
import com.itemconfiguration.utils.ItemUtils;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class DefaultItemSaveStrategy implements ItemSaveStrategy {

    private final RboPropertiesService rboPropertiesService;
    private final ItemService itemService;

    public DefaultItemSaveStrategy(RboPropertiesService rboPropertiesService,
            ItemService itemService) {
        this.rboPropertiesService = rboPropertiesService;
        this.itemService = itemService;
    }

    @Override
    public void save(CopyItemDto dto) {
        if (dto.getCopyFieldsStrategy() == CopyFieldsStrategy.FROM_COPY_ITEM || dto.getItems().size() == 1) {
            copyFromItemById(dto);
        } else {
            copyFromSuitableItem(dto);
        }
        itemService.saveAll(dto.getItems());
    }

    protected void copyFromItem(Item newItem, Item copyItem) {
        copyItemPrintTypes(newItem, copyItem);
    }

    protected void copyItemPrintTypes(Item newItem, Item copyItem) {
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(copyItem.getItemPrintTypes())) {
            copyItem.getItemPrintTypes()
                    .stream()
                    .map(ItemPrintType::new)
                    .forEach(newItem::addItemPrintType);
        }
    }

    private void copyFromSuitableItem(CopyItemDto dto) {
        List<ItemWithFieldsMap> itemsToCopy = getItemsToCopy(dto);
        if (CollectionUtils.isEmpty(itemsToCopy)) {
            throw new IllegalStateException("There are no items to copy with item number: " + dto.getCopyItemNumber());
        }
        Item defaultItem = findDefaultItem(itemsToCopy, dto.getCopyItemId());
        List<String> multipleFields = getReversedMultipleFields();
        for (Item newItem : dto.getItems()) {
            Item suitableItem = findSuitableItem(itemsToCopy, ItemUtils.convertToItemWithFieldsMap(newItem), multipleFields, defaultItem);
            copyFromItem(newItem, suitableItem);
        }
    }

    private Item findSuitableItem(List<ItemWithFieldsMap> itemsToCopy, ItemWithFieldsMap newItem, List<String> multipleFields, Item defaultItem) {
        if (itemsToCopy.size() == 1) {
            return itemsToCopy.get(0).getItem();
        }
        Item result = defaultItem;
        int suitableItemPriority = -1;

        for (ItemWithFieldsMap itemToCopy : itemsToCopy) {
            int currentPriority = 0;
            int fieldPrioroty = 1;
            for (String fieldName : multipleFields) {
                if (itemToCopy.hasField(fieldName) && newItem.hasField(fieldName)) {
                    if (itemToCopy.getFieldValue(fieldName).equalsIgnoreCase(newItem.getFieldValue(fieldName))) {
                        currentPriority += Math.pow(10, fieldPrioroty);
                    }
                }
                fieldPrioroty++;
            }
            if (currentPriority == 0) {
                continue;
            }
            if (suitableItemPriority < currentPriority) {
                suitableItemPriority = currentPriority;
                result = itemToCopy.getItem();
            }
        }
        return result;
    }

    private List<String> getReversedMultipleFields() {
        List<String> result = new LinkedList<>(rboPropertiesService.getMultipleFields());
        Collections.reverse(result);
        return result;
    }

    private void copyFromItemById(CopyItemDto dto) {
        itemService.getById(dto.getCopyItemId())
                .ifPresent(copyItem -> {
                    dto.getItems().forEach(newItem -> copyFromItem(newItem, copyItem));
                });
    }

    private Item findDefaultItem(List<ItemWithFieldsMap> itemsToCopy, Long copyItemId) {
        return itemsToCopy.stream()
                .map(ItemWithFieldsMap::getItem)
                .filter(item -> item.getId().equals(copyItemId))
                .findFirst()
                .orElse(null);
    }

    private List<ItemWithFieldsMap> getItemsToCopy(CopyItemDto dto) {
        return ItemUtils.convertToItemsWithFieldsMap(itemService.findByItemNumber(dto.getCopyItemNumber()));
    }
}
