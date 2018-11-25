package com.itemconfiguration.service.savestrategy.item.copy;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component("WithMandatoryDataCopyStrategy")
public class WithMandatoryDataCopyStrategy implements ItemFieldConfigsCopyStrategy {

    @Override
    public void copy(List<ItemFieldConfig> itemFieldConfigsToCopy, Item owner) {
        if (CollectionUtils.isEmpty(itemFieldConfigsToCopy)) {
            return;
        }
        List<ItemFieldConfig> itemFieldConfigsCopy = itemFieldConfigsToCopy.stream()
                .map(ItemFieldConfig::copyWithMandatoryData)
                .peek(itemFieldConfig -> itemFieldConfig.setItem(owner))
                .collect(Collectors.toList());
        owner.setItemFieldConfigs(itemFieldConfigsCopy);
    }
}
