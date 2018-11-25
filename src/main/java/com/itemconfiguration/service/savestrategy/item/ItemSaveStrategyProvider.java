package com.itemconfiguration.service.savestrategy.item;

import com.itemconfiguration.dto.CopyItemDto;
import com.itemconfiguration.service.savestrategy.item.strategy.ItemSaveStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ItemSaveStrategyProvider {

    @Resource(name = "DefaultItemSaveStrategy")
    private ItemSaveStrategy defaultStrategy;

    @Resource(name = "WithMandatoryDataSaveStrategy")
    private ItemSaveStrategy withMandatoryDataStrategy;

    @Resource(name = "OnlyItemFieldConfigsSaveStrategy")
    private ItemSaveStrategy onlyItemFieldConfigs;

    public ItemSaveStrategy getSaveStrategy(CopyItemDto copyItemDto) {
        if (copyItemDto.isWithItemFieldConfigs()) {
            if (copyItemDto.isWithMandatoryData()) {
                return withMandatoryDataStrategy;
            }
            return onlyItemFieldConfigs;
        }
        return defaultStrategy;
    }

}
