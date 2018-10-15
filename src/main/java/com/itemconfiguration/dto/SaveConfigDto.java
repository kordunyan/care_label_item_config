package com.itemconfiguration.dto;

import com.itemconfiguration.service.savestrategy.SaveForAllStrategy;

public class SaveConfigDto extends ItemCrudOperationsDto{

	private boolean saveForAll;

	private SaveForAllStrategy saveForAllStrategy;

	public SaveConfigDto() {
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
