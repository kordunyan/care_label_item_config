package com.itemconfiguration.dto;

import com.itemconfiguration.service.savestrategy.SaveForAllStrategy;

public class SaveConfigDto extends ItemCrudOperationsDto{

	private SaveForAllStrategy saveForAllStrategy;

	public SaveConfigDto() {
	}

	public SaveForAllStrategy getSaveForAllStrategy() {
		return saveForAllStrategy;
	}

	public void setSaveForAllStrategy(SaveForAllStrategy saveForAllStrategy) {
		this.saveForAllStrategy = saveForAllStrategy;
	}
}
