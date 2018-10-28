package com.itemconfiguration.dto;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.service.savestrategy.SaveForAllStrategy;

import java.util.ArrayList;
import java.util.List;

public class SaveMandatoryDataDto {
	private List<ItemFieldConfig> itemFieldConfigs;
	private boolean saveForAll;
	private List<String> itemNumbers = new ArrayList<>();

	public SaveMandatoryDataDto() {
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

	public List<String> getItemNumbers() {
		return itemNumbers;
	}

	public void setItemNumbers(List<String> itemNumbers) {
		this.itemNumbers = itemNumbers;
	}
}
