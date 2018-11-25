package com.itemconfiguration.service.savestrategy.item.strategy;

import com.itemconfiguration.dto.CopyItemDto;

public interface ItemSaveStrategy {
    void save(CopyItemDto copyItemDto);
}
