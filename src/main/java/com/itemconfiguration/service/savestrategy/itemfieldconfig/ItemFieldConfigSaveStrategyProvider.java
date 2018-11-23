package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.dto.ItemCrudOperationsDto;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ItemFieldConfigSaveStrategyProvider {

	@Resource(name = "default")
	ItemFieldConfigSaveStrategy defaultItemFieldConfigSaveStrategy;

	@Resource(name = "override")
	ItemFieldConfigSaveStrategy overrideChangedItemFieldConfigSaveStrategy;

	@Resource(name = "onlynew")
	ItemFieldConfigSaveStrategy onlyNewdefaultItemFieldConfigSaveStrategy;

	public ItemFieldConfigSaveStrategy getSaveStrategy(ItemCrudOperationsDto dto) {
		if (dto.isForAll()) {
			return getSaveForAllStrategy(dto);
		}
		return defaultItemFieldConfigSaveStrategy;
	}

	private ItemFieldConfigSaveStrategy getSaveForAllStrategy(ItemCrudOperationsDto dto) {
		switch (dto.getItemFieldsCriteria().getSaveForAllStrategy()) {
			case ONLY_FOR_NEW:
				return onlyNewdefaultItemFieldConfigSaveStrategy;
			case OVERRIDE_CHANGED:
				return overrideChangedItemFieldConfigSaveStrategy;
		}
		throw new IllegalArgumentException("There are now save implements save strategy " + dto.getItemFieldsCriteria().getSaveForAllStrategy());
	}
}
