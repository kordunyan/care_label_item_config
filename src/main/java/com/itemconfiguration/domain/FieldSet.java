package com.itemconfiguration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "field_set")
public class FieldSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long fieldSetId;

	public FieldSet() {
	}

	public Long getFieldSetId() {
		return fieldSetId;
	}

	public void setFieldSetId(Long fieldSetId) {
		this.fieldSetId = fieldSetId;
	}
}
