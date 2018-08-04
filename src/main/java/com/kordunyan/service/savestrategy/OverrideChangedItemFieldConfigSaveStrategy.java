package com.kordunyan.service.savestrategy;

import com.kordunyan.domain.AppFields;
import com.kordunyan.domain.Item;
import com.kordunyan.domain.ItemFieldConfig;
import com.kordunyan.domain.wrapper.ItemWithFieldsMap;
import com.kordunyan.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.kordunyan.dto.SaveItemFieldConfigDto;
import com.kordunyan.exception.SaveItemFieldConfigException;
import com.kordunyan.service.ItemFieldConfigService;
import com.kordunyan.service.ItemService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component("override")
public class OverrideChangedItemFieldConfigSaveStrategy implements ItemFieldConfigSaveStrategy {

	private ItemService itemService;
	private ItemFieldConfigService itemFieldConfigService;

	public OverrideChangedItemFieldConfigSaveStrategy(ItemService itemService,
			ItemFieldConfigService itemFieldConfigService) {
		this.itemService = itemService;
		this.itemFieldConfigService = itemFieldConfigService;
	}

	@Override
	public void save(SaveItemFieldConfigDto saveItemFieldConfigDto) throws SaveItemFieldConfigException {
		List<ItemFieldConfig> changedItemFieldsConfigs = saveItemFieldConfigDto.getItemFieldConfigs();
		Item originalItem = saveItemFieldConfigDto.getItem();
		if (CollectionUtils.isEmpty(changedItemFieldsConfigs)) {
			return;
		}
		List<ItemFieldConfig> itemFieldConfigsForSave = new ArrayList<>();
		itemFieldConfigsForSave.addAll(getUpdatedOriginalItemFields(originalItem, changedItemFieldsConfigs));
		itemFieldConfigsForSave.addAll(getUpdatedItemsFields(getAllItemsByItemNumber(originalItem), changedItemFieldsConfigs));
		itemFieldConfigService.saveAll(itemFieldConfigsForSave);
	}

	private List<ItemFieldConfig> getUpdatedItemsFields(List<ItemWithItemFieldConfigsMap> items, List<ItemFieldConfig> changedItemFieldsConfigs) {
		List<ItemFieldConfig> result = new ArrayList<>();
		for (ItemWithItemFieldConfigsMap item : items) {
			for (ItemFieldConfig changedItemFieldConfig : changedItemFieldsConfigs) {
				ItemFieldConfig changedItemFieldConfigCopy = ItemFieldConfig.copyWithoutIdAndItem(changedItemFieldConfig);
				if (item.containsItemFieldConfig(changedItemFieldConfig.getFieldConfigName())) {
					changedItemFieldConfigCopy.setId(item.getItemFieldConfig(changedItemFieldConfig.getFieldConfigName()).getId());
				}
				changedItemFieldConfigCopy.setItem(item.getItem());
				result.add(changedItemFieldConfigCopy);
			}
		}
		return result;
	}

	private List<ItemWithItemFieldConfigsMap> getAllItemsByItemNumber(Item originalItem) throws SaveItemFieldConfigException {
		ItemWithFieldsMap originalItemWithFildsMap = new ItemWithFieldsMap(originalItem);
		if (!originalItemWithFildsMap.containsField(AppFields.D2COMM_ITEM_NUMBER)) {
			throw new SaveItemFieldConfigException(AppFields.D2COMM_ITEM_NUMBER + " field does not exist in item");
		}
		List<Item> items = itemService.findByItemNumber(originalItemWithFildsMap.getFieldValue(AppFields.D2COMM_ITEM_NUMBER));
		return items.stream()
				.filter(item -> !item.getId().equals(originalItem.getId()))
				.map(ItemWithItemFieldConfigsMap::new)
				.collect(Collectors.toList());
	}

	private List<ItemFieldConfig> getUpdatedOriginalItemFields(Item item, List<ItemFieldConfig> itemFieldConfigs) {
		itemFieldConfigs.forEach(itemFieldConfig -> itemFieldConfig.setItem(item));
		return itemFieldConfigs;
	}
}
