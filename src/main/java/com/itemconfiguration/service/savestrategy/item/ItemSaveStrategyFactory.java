package com.itemconfiguration.service.savestrategy.item;


import com.itemconfiguration.service.ItemService;
import com.itemconfiguration.service.RboPropertiesService;
import com.itemconfiguration.service.savestrategy.item.strategy.DefaultItemSaveStrategy;
import com.itemconfiguration.service.savestrategy.item.copy.ItemFieldConfigsCopyStrategy;
import com.itemconfiguration.service.savestrategy.item.strategy.ItemSaveStrategy;
import com.itemconfiguration.service.savestrategy.item.strategy.WithItemFieldConfigsSaveStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemSaveStrategyFactory {

    @Bean("OnlyItemFieldConfigsSaveStrategy")
    public ItemSaveStrategy onlyItemFieldConfigsSaveStrategy(
            @Qualifier("OnlyItemFieldConfigsCopyStrategy") ItemFieldConfigsCopyStrategy itemFieldConfigsCopyStrategy,
            ItemService itemService, RboPropertiesService rboPropertiesService) {
        return new WithItemFieldConfigsSaveStrategy(itemService, itemFieldConfigsCopyStrategy, rboPropertiesService);
    }

    @Bean("WithMandatoryDataSaveStrategy")
    public ItemSaveStrategy withMandatoryDataSaveStrategy(
            @Qualifier("WithMandatoryDataCopyStrategy") ItemFieldConfigsCopyStrategy itemFieldConfigsCopyStrategy,
            ItemService itemService, RboPropertiesService rboPropertiesService) {
        return new WithItemFieldConfigsSaveStrategy(itemService, itemFieldConfigsCopyStrategy, rboPropertiesService);
    }

    @Bean("DefaultItemSaveStrategy")
    public ItemSaveStrategy defaultItemSaveStrategy(ItemService itemService) {
        return new DefaultItemSaveStrategy(itemService);
    }
}
