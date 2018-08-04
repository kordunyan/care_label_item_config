package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.dto.SaveItemFieldConfigDto;
import com.itemconfiguration.exception.SaveItemFieldConfigException;

public interface ItemFieldConfigSaveStrategy {
	void save(SaveItemFieldConfigDto saveItemFieldConfigDto) throws SaveItemFieldConfigException;
}
