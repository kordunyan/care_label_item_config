package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.ItemCrudOperationsDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component("default")
public class DefaultItemFieldConfigSaveStrategy implements ItemFieldConfigSaveStrategy {

	private ItemFieldConfigService itemFieldConfigService;

	public DefaultItemFieldConfigSaveStrategy(ItemFieldConfigService itemFieldConfigService) {
		this.itemFieldConfigService = itemFieldConfigService;
	}

	@Override
	public void save(ItemCrudOperationsDto dto) {
		List<ItemFieldConfig> changedItemFieldConfigs = dto.getItemFieldConfigs();
		if (CollectionUtils.isEmpty(changedItemFieldConfigs)) {
			return;
		}
		Item item = dto.getItem();
		itemFieldConfigService.saveAll(setItemForChangedFieldConfigs(item, changedItemFieldConfigs));
	}

	private List<ItemFieldConfig> setItemForChangedFieldConfigs(Item item, List<ItemFieldConfig> itemFieldConfigs) {
		return itemFieldConfigs.stream()
				.peek(itemFieldConfig -> itemFieldConfig.setItem(item))
				.collect(Collectors.toList());
	}
}
