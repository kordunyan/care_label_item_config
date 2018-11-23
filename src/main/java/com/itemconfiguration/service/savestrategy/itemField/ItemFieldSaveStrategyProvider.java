package com.itemconfiguration.service.savestrategy.itemField;

import com.itemconfiguration.dto.ItemFieldCrudOperationsDto;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ItemFieldSaveStrategyProvider {
	@Resource(name = "overrideFields")
	private ItemFieldSaveStrategy overrideChangedSaveStrategy;

	@Resource(name = "onlyNewFields")
	private ItemFieldSaveStrategy onlyNewSaveStrategy;

	public ItemFieldSaveStrategyProvider() {
	}

	public ItemFieldSaveStrategy getStrategy(ItemFieldCrudOperationsDto dto) {
		switch (dto.getItemFieldsCriteria().getSaveForAllStrategy()){
			case ONLY_FOR_NEW:
				return onlyNewSaveStrategy;
			case OVERRIDE_CHANGED:
				return overrideChangedSaveStrategy;
			default:
				throw new IllegalStateException("There are any field save strategy");
		}
	}

}
