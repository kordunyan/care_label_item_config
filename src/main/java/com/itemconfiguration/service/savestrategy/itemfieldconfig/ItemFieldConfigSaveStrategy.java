package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.dto.ItemCrudOperationsDto;
import com.itemconfiguration.exception.SaveItemFieldConfigException;

public interface ItemFieldConfigSaveStrategy {
	void save(ItemCrudOperationsDto saveConfigDto) throws SaveItemFieldConfigException;
}
