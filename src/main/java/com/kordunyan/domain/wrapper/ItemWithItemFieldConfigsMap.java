package com.kordunyan.domain.wrapper;

import com.kordunyan.domain.Item;
import com.kordunyan.domain.ItemFieldConfig;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

public class ItemWithItemFieldConfigsMap {
	private Item item;

	private Map<String, ItemFieldConfig> itemFieldConfigMap = new HashMap<>();

	public ItemWithItemFieldConfigsMap(Item item) {
		this.item = item;
		initItemFieldConfigsMap();
	}

	private void initItemFieldConfigsMap() {
		if (item == null || CollectionUtils.isEmpty(item.getItemFieldConfigs())) {
			return;
		}
		item.getItemFieldConfigs().forEach(field -> itemFieldConfigMap.put(field.getFieldConfigName(), field));
	}

	public Item getItem() {
		return item;
	}

	public ItemFieldConfig getItemFieldConfig(String fieldConfigName) {
		return itemFieldConfigMap.get(fieldConfigName);
	}

	public boolean containsItemFieldConfig(String fieldConfigName) {
		return itemFieldConfigMap.containsKey(fieldConfigName);
	}
}
