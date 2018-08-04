package com.itemconfiguration.service.savestrategy;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("override")
public class OverrideChangedItemFieldConfigSaveStrategy extends AbstractSaveForAllStrategy {

	public OverrideChangedItemFieldConfigSaveStrategy(ItemService itemService,
			ItemFieldConfigService itemFieldConfigService) {
		super(itemService, itemFieldConfigService);
	}

	protected void addChangedFieldConfig(List<ItemFieldConfig> result, ItemWithItemFieldConfigsMap item, ItemFieldConfig changedItemFieldConfig) {
		ItemFieldConfig changedItemFieldConfigCopy = ItemFieldConfig.copyWithoutIdAndItem(changedItemFieldConfig);
		if (item.containsItemFieldConfig(changedItemFieldConfig.getFieldConfigName())) {
			changedItemFieldConfigCopy.setId(item.getItemFieldConfig(changedItemFieldConfig.getFieldConfigName()).getId());
		}
		changedItemFieldConfigCopy.setItem(item.getItem());
		result.add(changedItemFieldConfigCopy);
	}
}
