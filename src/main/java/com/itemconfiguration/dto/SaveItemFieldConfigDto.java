package com.itemconfiguration.dto;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.service.savestrategy.itemfieldconfig.SaveForAllStrategy;

import java.util.List;

public class SaveItemFieldConfigDto extends ItemWithItemFieldConfigDto{

	private boolean saveForAll;

	private SaveForAllStrategy saveForAllStrategy;

	public SaveItemFieldConfigDto() {
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
