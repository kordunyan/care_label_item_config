package com.itemconfiguration.service.deletestrategy.itemfieldconfig;

import com.itemconfiguration.dto.ItemWithItemFieldConfigDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import org.springframework.stereotype.Component;

@Component("original")
public class DeleteForOriginalItemStrategy implements DeleteItemFieldConfigStrategy{

    private ItemFieldConfigService itemFieldConfigService;

    public DeleteForOriginalItemStrategy(ItemFieldConfigService itemFieldConfigService) {
        this.itemFieldConfigService = itemFieldConfigService;
    }

    @Override
    public void delete(ItemWithItemFieldConfigDto itemWithItemFieldConfigDto) {
        itemFieldConfigService.deleteItemFieldConfigs(itemWithItemFieldConfigDto.getItemFieldConfigs());
    }
}
