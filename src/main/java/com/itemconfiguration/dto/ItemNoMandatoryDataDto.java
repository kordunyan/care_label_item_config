package com.itemconfiguration.dto;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.FieldSet;
import com.itemconfiguration.domain.ItemFieldConfig;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ItemNoMandatoryDataDto {
	private Long id;
	private boolean ipps;
	private boolean sb;
	private FieldSet fieldSet;
	private List<Field> fields = new ArrayList<>();
	private List<ItemFieldConfigNoMandatoryDataDto> itemFieldConfigs = new ArrayList<>();

	public ItemNoMandatoryDataDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isIpps() {
		return ipps;
	}

	public void setIpps(boolean ipps) {
		this.ipps = ipps;
	}

	public boolean isSb() {
		return sb;
	}

	public void setSb(boolean sb) {
		this.sb = sb;
	}

	public FieldSet getFieldSet() {
		return fieldSet;
	}

	public void setFieldSet(FieldSet fieldSet) {
		this.fieldSet = fieldSet;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<ItemFieldConfigNoMandatoryDataDto> getItemFieldConfigs() {
		return itemFieldConfigs;
	}

	public void addItemFiieldConfig(ItemFieldConfigNoMandatoryDataDto itemFieldConfig) {
		this.itemFieldConfigs.add(itemFieldConfig);
	}

	public void setItemFieldConfigs(List<ItemFieldConfigNoMandatoryDataDto> itemFieldConfigs) {
		this.itemFieldConfigs = itemFieldConfigs;
	}
}
