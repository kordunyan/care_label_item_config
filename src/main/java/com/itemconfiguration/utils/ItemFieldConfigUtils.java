package com.itemconfiguration.utils;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.ItemFieldsCriteriaDto;
import com.itemconfiguration.dto.MultipleField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;

public class ItemFieldConfigUtils {

    public static Map<String, List<ItemFieldConfig>> createItemFieldConfigsMap(List<ItemFieldConfig> itemFieldConfigs) {
        if (CollectionUtils.isEmpty(itemFieldConfigs)) {
            return Collections.emptyMap();
        }
        Map<String, List<ItemFieldConfig>> result = new HashMap<>();
        for (ItemFieldConfig itemFieldConfig : itemFieldConfigs) {
            String fieldConfigName = itemFieldConfig.getFieldConfig().getName();
            if (!result.containsKey(fieldConfigName)) {
                result.put(fieldConfigName, new ArrayList<>());
            }
            result.get(fieldConfigName).add(itemFieldConfig);
        }
        return result;
    }

    public static List<ItemFieldConfig> filterByitemFieldsCriteria(List<ItemFieldConfig> itemFieldConfigs, ItemFieldsCriteriaDto criteria) {
        if (CollectionUtils.isEmpty(itemFieldConfigs) || !ItemUtils.shouldFilter(criteria)) {
            return itemFieldConfigs;
        }
        Map<Long, Boolean> itemMultipleFieldsStatus = new HashMap<>();
        List<ItemFieldConfig> result = new ArrayList<>();
        for (ItemFieldConfig itemFieldConfig : itemFieldConfigs) {
            Item item = itemFieldConfig.getItem();
            if (!itemMultipleFieldsStatus.containsKey(item.getId())) {
                itemMultipleFieldsStatus.put(item.getId(), ItemUtils.fitsByCriteria(item, criteria));
            }
            if (itemMultipleFieldsStatus.get(item.getId())) {
                result.add(itemFieldConfig);
            }
        }
        return result;
    }

}
