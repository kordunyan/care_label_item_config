package com.kordunyan.service.savestrategy;

import com.kordunyan.dto.SaveItemFieldConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemFieldConfigSaveStrategyProvider {

	@Resource(name = "default")
	ItemFieldConfigSaveStrategy defaultItemFieldConfigSaveStrategy;

	public ItemFieldConfigSaveStrategy getSaveStrategy(SaveItemFieldConfigDto saveItemFieldConfigDto) {
		if (saveItemFieldConfigDto.isSaveForAll()) {

		}
		return defaultItemFieldConfigSaveStrategy;
	}
}
