package com.itemconfiguration.service.savestrategy.item.strategy;

import com.itemconfiguration.dto.CopyItemDto;
import com.itemconfiguration.service.ItemService;
import org.springframework.util.CollectionUtils;


public class DefaultItemSaveStrategy implements ItemSaveStrategy {

    private final ItemService itemService;

    public DefaultItemSaveStrategy(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void save(CopyItemDto dto) {
        if (CollectionUtils.isEmpty(dto.getItems())) {
            return;
        }
        itemService.saveAll(dto.getItems());
    }
}
