package com.itemconfiguration.domain;

import javax.persistence.*;

@Entity
@Table(name = "field_set")
public class FieldSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "field_set_id")
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
