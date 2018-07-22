package com.kordunyan.dto;

import com.kordunyan.domain.Field;
import com.kordunyan.domain.FieldSet;

import java.util.List;

public class FieldForAllItemsDto {
	private Field field;
	private List<FieldSet> fieldSets;

	public FieldForAllItemsDto() {
	}

	public FieldForAllItemsDto(Field field, List<FieldSet> fieldSets) {
		this.field = field;
		this.fieldSets = fieldSets;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public List<FieldSet> getFieldSets() {
		return fieldSets;
	}

	public void setFieldSets(List<FieldSet> fieldSets) {
		this.fieldSets = fieldSets;
	}
}
