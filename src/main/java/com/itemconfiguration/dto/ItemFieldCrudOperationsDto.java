package com.itemconfiguration.dto;

import com.itemconfiguration.domain.Field;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

public class ItemFieldCrudOperationsDto {
	private List<String> itemNumbers;
	private List<Field> fields;
	private ItemFieldsCriteriaDto itemFieldsCriteria;


	public ItemFieldCrudOperationsDto() {
	}

	public boolean hasItemNumbers() {
		return CollectionUtils.isNotEmpty(this.itemNumbers);
	}

	public boolean hasFields() {
		return CollectionUtils.isNotEmpty(this.fields);
	}

	public ItemFieldsCriteriaDto getItemFieldsCriteria() {
		return itemFieldsCriteria;
	}

	public void setItemFieldsCriteria(ItemFieldsCriteriaDto itemFieldsCriteria) {
		this.itemFieldsCriteria = itemFieldsCriteria;
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
