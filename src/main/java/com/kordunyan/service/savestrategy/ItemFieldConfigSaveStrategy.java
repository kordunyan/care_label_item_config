package com.kordunyan.service.savestrategy;

import com.kordunyan.dto.SaveItemFieldConfigDto;
import com.kordunyan.exception.SaveItemFieldConfigException;

public interface ItemFieldConfigSaveStrategy {
	void save(SaveItemFieldConfigDto saveItemFieldConfigDto) throws SaveItemFieldConfigException;
}
