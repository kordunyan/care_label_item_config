package com.kordunyan.dto;

import com.kordunyan.domain.Item;
import com.kordunyan.domain.ItemFieldConfig;

import java.util.List;

public class SaveItemFieldConfigDto {
	private Item item;

	private List<ItemFieldConfig> itemFieldConfigs;

	private boolean saveForAll;

	private boolean newOnlyForAll;

	public SaveItemFieldConfigDto() {
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<ItemFieldConfig> getItemFieldConfigs() {
		return itemFieldConfigs;
	}

	public void setItemFieldConfigs(List<ItemFieldConfig> itemFieldConfigs) {
		this.itemFieldConfigs = itemFieldConfigs;
	}

	public boolean isSaveForAll() {
		return saveForAll;
	}

	public void setSaveForAll(boolean saveForAll) {
		this.saveForAll = saveForAll;
	}

	public boolean isNewOnlyForAll() {
		return newOnlyForAll;
	}

	public void setNewOnlyForAll(boolean newOnlyForAll) {
		this.newOnlyForAll = newOnlyForAll;
	}
}
