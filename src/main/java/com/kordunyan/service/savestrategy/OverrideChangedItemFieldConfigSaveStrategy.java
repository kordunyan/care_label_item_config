package com.kordunyan.service.savestrategy;

import com.kordunyan.domain.ItemFieldConfig;
import com.kordunyan.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.kordunyan.service.ItemFieldConfigService;
import com.kordunyan.service.ItemService;
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
