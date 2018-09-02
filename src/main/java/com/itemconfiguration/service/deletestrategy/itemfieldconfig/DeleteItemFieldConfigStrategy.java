package com.itemconfiguration.service.deletestrategy.itemfieldconfig;

import com.itemconfiguration.dto.ItemCrudOperationsDto;
import com.itemconfiguration.dto.ItemWithItemFieldConfigDto;
import com.itemconfiguration.exception.SaveItemFieldConfigException;

public interface DeleteItemFieldConfigStrategy {
    void delete(ItemCrudOperationsDto curdOperationsDto) throws SaveItemFieldConfigException;
}
