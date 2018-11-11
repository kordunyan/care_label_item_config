package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.dto.SaveConfigDto;
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

	public ItemFieldConfigSaveStrategy getSaveStrategy(SaveConfigDto saveConfigDto) {
		if (saveConfigDto.isForAll()) {
			return getSaveForAllStrategy(saveConfigDto);
		}
		return defaultItemFieldConfigSaveStrategy;
	}

	private ItemFieldConfigSaveStrategy getSaveForAllStrategy(SaveConfigDto saveConfigDto) {
		switch (saveConfigDto.getSaveForAllStrategy()) {
			case ONLY_FOR_NEW:
				return onlyNewdefaultItemFieldConfigSaveStrategy;
			case OVERRIDE_CHANGED:
				return overrideChangedItemFieldConfigSaveStrategy;
		}
		throw new IllegalArgumentException("There are now save implements save strategy " + saveConfigDto.getSaveForAllStrategy());
	}
}
