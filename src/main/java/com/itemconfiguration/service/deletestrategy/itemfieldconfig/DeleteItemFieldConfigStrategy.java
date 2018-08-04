package com.itemconfiguration.service.deletestrategy.itemfieldconfig;

import com.itemconfiguration.dto.ItemWithItemFieldConfigDto;
import com.itemconfiguration.exception.SaveItemFieldConfigException;

public interface DeleteItemFieldConfigStrategy {
    void delete(ItemWithItemFieldConfigDto itemWithItemFieldConfigDto) throws SaveItemFieldConfigException;
}
