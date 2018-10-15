package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.dto.SaveConfigDto;
import com.itemconfiguration.exception.SaveItemFieldConfigException;

public interface ItemFieldConfigSaveStrategy {
	void save(SaveConfigDto saveConfigDto) throws SaveItemFieldConfigException;
}
