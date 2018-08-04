package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.dto.SaveItemFieldConfigDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemFieldConfigSaveStrategyProvider {

	@Resource(name = "default")
	ItemFieldConfigSaveStrategy defaultItemFieldConfigSaveStrategy;

	@Resource(name = "override")
	ItemFieldConfigSaveStrategy overrideChangedItemFieldConfigSaveStrategy;

	@Resource(name = "onlynew")
	ItemFieldConfigSaveStrategy onlyNewdefaultItemFieldConfigSaveStrategy;

	public ItemFieldConfigSaveStrategy getSaveStrategy(SaveItemFieldConfigDto saveItemFieldConfigDto) {
		if (saveItemFieldConfigDto.isSaveForAll()) {
			return getSaveForAllStrategy(saveItemFieldConfigDto);
		}
		return defaultItemFieldConfigSaveStrategy;
	}

	private ItemFieldConfigSaveStrategy getSaveForAllStrategy(SaveItemFieldConfigDto saveItemFieldConfigDto) {
		switch (saveItemFieldConfigDto.getSaveForAllStrategy()) {
			case ONLY_FOR_NEW:
				return onlyNewdefaultItemFieldConfigSaveStrategy;
			case OVERRIDE_CHANGED:
				return overrideChangedItemFieldConfigSaveStrategy;
		}
		throw new IllegalArgumentException("There are now save implements save strategy " + saveItemFieldConfigDto.getSaveForAllStrategy());
	}
}
