package com.itemconfiguration.service.deletestrategy.itemfieldconfig;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.ItemCrudOperationsDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component("DeleteForOriginalItemStrategy")
public class DeleteForOriginalItemStrategy implements DeleteItemFieldConfigStrategy{

    private ItemFieldConfigService itemFieldConfigService;

    public DeleteForOriginalItemStrategy(ItemFieldConfigService itemFieldConfigService) {
        this.itemFieldConfigService = itemFieldConfigService;
    }

    @Override
    public void delete(ItemCrudOperationsDto curdOperationsDto) {
        List<ItemFieldConfig> itemFieldConfigs = itemFieldConfigService.getAllByIds(getIds(curdOperationsDto.getItemFieldConfigs()));
        itemFieldConfigService.deleteItemFieldConfigs(itemFieldConfigs);
    }

    private List<Long> getIds(List<ItemFieldConfig> itemFieldConfigs) {
        if (CollectionUtils.isEmpty(itemFieldConfigs)) {
            return Collections.emptyList();
        }
        return itemFieldConfigs.stream()
                .map(ItemFieldConfig::getId)
                .collect(Collectors.toList());
    }
}
