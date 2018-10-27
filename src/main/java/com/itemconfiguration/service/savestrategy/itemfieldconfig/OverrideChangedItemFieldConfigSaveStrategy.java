package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("override")
public class OverrideChangedItemFieldConfigSaveStrategy extends AbstractItemFieldConfigSaveForAllStrategy {

	public OverrideChangedItemFieldConfigSaveStrategy(ItemService itemService,
			ItemFieldConfigService itemFieldConfigService) {
		super(itemService, itemFieldConfigService);
	}

	protected void addChangedFieldConfig(List<ItemFieldConfig> result, ItemWithItemFieldConfigsMap item, ItemFieldConfig changedItemFieldConfig) {
		ItemFieldConfig changedItemFieldConfigCopy = ItemFieldConfig.copyWithoutIdAndItem(changedItemFieldConfig);
		ItemFieldConfig itemFieldConfig = item.getItemFieldConfig(changedItemFieldConfig.getFieldConfig().getName());
		if (itemFieldConfig != null) {
			changedItemFieldConfigCopy.setId(itemFieldConfig.getId());
		}
		changedItemFieldConfigCopy.setItem(item.getItem());
		result.add(changedItemFieldConfigCopy);
	}
}
