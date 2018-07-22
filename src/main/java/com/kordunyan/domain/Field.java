package com.kordunyan.domain;

import javax.persistence.*;

@Entity
@Table(
		name = "field",
		uniqueConstraints = @UniqueConstraint(
				name = "field_config_name_set_id",
				columnNames = {"field_config_name", "field_set_id"}
				)
)
public class Field {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "text")
	private String value;

	@Column(name = "field_config_name")
	private String fieldConfigName;

	@OneToOne
	@JoinColumn(name = "field_set_id", foreignKey = @ForeignKey())
	private FieldSet fieldSet;

	public Field() {
	}

	public Field(String fieldConfigName, String value, FieldSet fieldSet) {
		this.value = value;
		this.fieldConfigName = fieldConfigName;
		this.fieldSet = fieldSet;
	}

	public Field(Field field) {
		this.value = field.value;
		this.fieldConfigName = field.fieldConfigName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFieldConfigName() {
		return fieldConfigName;
	}

	public void setFieldConfigName(String fieldConfigName) {
		this.fieldConfigName = fieldConfigName;
	}

	public FieldSet getFieldSet() {
		return fieldSet;
	}

	public void setFieldSet(FieldSet fieldSet) {
		this.fieldSet = fieldSet;
	}
}
