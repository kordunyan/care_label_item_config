package com.itemconfiguration.service.savestrategy.itemfieldconfig;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.SaveItemFieldConfigDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("default")
public class DefaultItemFieldConfigSaveStrategy implements ItemFieldConfigSaveStrategy {

	private ItemFieldConfigService itemFieldConfigService;

	public DefaultItemFieldConfigSaveStrategy(ItemFieldConfigService itemFieldConfigService) {
		this.itemFieldConfigService = itemFieldConfigService;
	}

	@Override
	public void save(SaveItemFieldConfigDto saveItemFieldConfigDto) {
		List<ItemFieldConfig> changedItemFieldConfigs = saveItemFieldConfigDto.getItemFieldConfigs();
		if (CollectionUtils.isEmpty(changedItemFieldConfigs)) {
			return;
		}
		Item item = saveItemFieldConfigDto.getItem();
		itemFieldConfigService.saveAll(getUpdatedOriginalItemFields(item, changedItemFieldConfigs));
	}

	private List<ItemFieldConfig> getUpdatedOriginalItemFields(Item item, List<ItemFieldConfig> itemFieldConfigs) {
		return itemFieldConfigs.stream()
				.peek(itemFieldConfig -> itemFieldConfig.setItem(item))
				.collect(Collectors.toList());
	}
}
