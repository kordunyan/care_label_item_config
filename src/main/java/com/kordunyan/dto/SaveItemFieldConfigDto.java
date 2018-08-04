package com.kordunyan.dto;

import com.kordunyan.domain.Item;
import com.kordunyan.domain.ItemFieldConfig;
import com.kordunyan.service.savestrategy.SaveForAllStrategy;

import java.util.List;

public class SaveItemFieldConfigDto {
	private Item item;

	private List<ItemFieldConfig> itemFieldConfigs;

	private boolean saveForAll;

	private SaveForAllStrategy saveForAllStrategy;

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

	public SaveForAllStrategy getSaveForAllStrategy() {
		return saveForAllStrategy;
	}

	public void setSaveForAllStrategy(SaveForAllStrategy saveForAllStrategy) {
		this.saveForAllStrategy = saveForAllStrategy;
	}
}
