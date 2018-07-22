package com.kordunyan.service.savestrategy;

import com.kordunyan.domain.Item;
import com.kordunyan.domain.ItemFieldConfig;
import com.kordunyan.dto.SaveItemFieldConfigDto;
import com.kordunyan.service.ItemFieldConfigService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("default")
public class DefaultItemFieldConfigSaveStrategy implements ItemFieldConfigSaveStrategy {

	private ItemFieldConfigService itemFieldConfigService;

	public DefaultItemFieldConfigSaveStrategy(ItemFieldConfigService itemFieldConfigService) {
		this.itemFieldConfigService = itemFieldConfigService;
	}

	@Override
	public void save(SaveItemFieldConfigDto saveItemFieldConfigDto) {
		Item item = saveItemFieldConfigDto.getItem();
		List<ItemFieldConfig> itemFieldConfigs = saveItemFieldConfigDto.getItemFieldConfigs();
		setItemToFieldConfigs(item, itemFieldConfigs);
		itemFieldConfigService.saveAll(itemFieldConfigs);
	}

	private void setItemToFieldConfigs(Item item, List<ItemFieldConfig> itemFieldConfigList) {
		for (ItemFieldConfig itemFieldConfig : itemFieldConfigList) {
			itemFieldConfig.setItem(item);
		}
	}
}
