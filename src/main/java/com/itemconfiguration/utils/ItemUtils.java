package com.itemconfiguration.utils;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.itemconfiguration.dto.MultipleField;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class ItemUtils {

    public static List<Item> filterByMultipleFields(List<Item> items, List<MultipleField> multipleFields) {
        return items.stream()
                .filter(itemWithFieldsMap -> containsAllMultipleFields(itemWithFieldsMap, multipleFields))
                .collect(Collectors.toList());
    }

    public static boolean containsAllMultipleFields(Item item, List<MultipleField> multipleFields) {
        ItemWithFieldsMap itemWithFieldsMap = new ItemWithFieldsMap(item);
        for (MultipleField multipleField : multipleFields) {
            if (CollectionUtils.isEmpty(multipleField.getValues())) {
                continue;
            }
            Field itemField = itemWithFieldsMap.getField(multipleField.getFieldConfigName());
            if (itemField == null) {
                return false;
            }
            for (String value : multipleField.getValues()) {
                if (!StringUtils.equalsIgnoreCase(value, itemField.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    public static List<ItemWithItemFieldConfigsMap> convertToItemWithItemFieldConfigsMap(List<Item> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream()
                .map(ItemWithItemFieldConfigsMap::new)
                .collect(Collectors.toList());
    }

    public static List<ItemWithFieldsMap> convertToItemWithFieldsMap(List<Item> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream()
                .map(ItemWithFieldsMap::new)
                .collect(Collectors.toList());
    }

}
