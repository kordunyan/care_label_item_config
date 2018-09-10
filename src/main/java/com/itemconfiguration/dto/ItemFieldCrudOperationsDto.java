package com.itemconfiguration.dto;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.service.savestrategy.SaveForAllStrategy;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ItemFieldCrudOperationsDto {
	private List<String> itemNumbers;
	private List<Field> fields;
	private SaveForAllStrategy saveForAllStrategy;

	public ItemFieldCrudOperationsDto() {
	}

	public boolean hasItemNumbers() {
		return CollectionUtils.isNotEmpty(this.itemNumbers);
	}

	public boolean hasFields() {
		return CollectionUtils.isNotEmpty(this.fields);
	}

	public SaveForAllStrategy getSaveForAllStrategy() {
		return saveForAllStrategy;
	}

	public void setSaveForAllStrategy(SaveForAllStrategy saveForAllStrategy) {
		this.saveForAllStrategy = saveForAllStrategy;
	}

	public List<String> getItemNumbers() {
		return itemNumbers;
	}

	public void setItemNumbers(List<String> itemNumbers) {
		this.itemNumbers = itemNumbers;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
}
