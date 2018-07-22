package com.kordunyan.dto;

import com.kordunyan.domain.Field;
import com.kordunyan.domain.FieldSet;

import java.util.List;

public class NewFieldsDto {
	private List<Field> fields;
	private List<FieldSet> fieldSets;

	public NewFieldsDto() {
	}

	public NewFieldsDto(List<Field> fields, List<FieldSet> fieldSets) {
		this.fields = fields;
		this.fieldSets = fieldSets;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<FieldSet> getFieldSets() {
		return fieldSets;
	}

	public void setFieldSets(List<FieldSet> fieldSets) {
		this.fieldSets = fieldSets;
	}
}
