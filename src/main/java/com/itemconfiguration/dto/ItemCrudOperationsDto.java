package com.itemconfiguration.dto;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;

import java.util.List;

public class ItemCrudOperationsDto {

	private Item item;

	private List<String> itemNumbers;

	private List<ItemFieldConfig> itemFieldConfigs;

	private boolean forAll;

	public ItemCrudOperationsDto() {
	}

	public boolean isForAll() {
		return forAll;
	}

	public void setForAll(boolean forAll) {
		this.forAll = forAll;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<String> getItemNumbers() {
		return itemNumbers;
	}

	public void setItemNumbers(List<String> itemNumbers) {
		this.itemNumbers = itemNumbers;
	}

	public List<ItemFieldConfig> getItemFieldConfigs() {
		return itemFieldConfigs;
	}

	public void setItemFieldConfigs(List<ItemFieldConfig> itemFieldConfigs) {
		this.itemFieldConfigs = itemFieldConfigs;
	}
}
