package com.itemconfiguration.dto;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.domain.MandatoryTranslation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ItemFieldConfigNoMandatoryDataDto {
	private Long id;
	private FieldConfig fieldConfig;
	private boolean isActive;
	private boolean isRequired;
	private boolean isEditable;
	private boolean storeLastUserInput;
	private String predefinedValue;
	private String filterRegex;
	private boolean canAddLater;
	private String dataSourceName;

	public ItemFieldConfigNoMandatoryDataDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FieldConfig getFieldConfig() {
		return fieldConfig;
	}

	public void setFieldConfig(FieldConfig fieldConfig) {
		this.fieldConfig = fieldConfig;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean required) {
		isRequired = required;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean editable) {
		isEditable = editable;
	}

	public boolean isStoreLastUserInput() {
		return storeLastUserInput;
	}

	public void setStoreLastUserInput(boolean storeLastUserInput) {
		this.storeLastUserInput = storeLastUserInput;
	}

	public String getPredefinedValue() {
		return predefinedValue;
	}

	public void setPredefinedValue(String predefinedValue) {
		this.predefinedValue = predefinedValue;
	}

	public String getFilterRegex() {
		return filterRegex;
	}

	public void setFilterRegex(String filterRegex) {
		this.filterRegex = filterRegex;
	}

	public boolean isCanAddLater() {
		return canAddLater;
	}

	public void setCanAddLater(boolean canAddLater) {
		this.canAddLater = canAddLater;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
}
