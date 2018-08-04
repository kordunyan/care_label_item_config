package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("onlynew")
public class OnlyNewItemFieldConfigSaveStrategy extends AbstractSaveForAllStrategy{

	public OnlyNewItemFieldConfigSaveStrategy(ItemService itemService,
			ItemFieldConfigService itemFieldConfigService) {
		super(itemService, itemFieldConfigService);
	}

	@Override
	protected void addChangedFieldConfig(List<ItemFieldConfig> result, ItemWithItemFieldConfigsMap item, ItemFieldConfig changedItemFieldConfig) {
		if (!item.containsItemFieldConfig(changedItemFieldConfig.getFieldConfigName())) {
			ItemFieldConfig changedItemFieldConfigCopy = ItemFieldConfig.copyWithoutIdAndItem(changedItemFieldConfig);
			changedItemFieldConfigCopy.setItem(item.getItem());
			result.add(changedItemFieldConfigCopy);
		}
	}
}
