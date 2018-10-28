package com.itemconfiguration.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class MandatoryField {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(
			name = "field_config_name",
			foreignKey = @ForeignKey(name = "mandatory_field_field_condif_name_fkey")
	)
	private FieldConfig fieldConfig;

	@ManyToOne()
	@JoinColumn(
			name = "item_field_config_id",
			foreignKey = @ForeignKey(name = "mandatory_field_item_field_config_id_fkey")
	)
	@JsonIgnore
	private ItemFieldConfig itemFieldConfig;

	public MandatoryField() {
	}

	public MandatoryField(ItemFieldConfig itemFieldConfig, FieldConfig fieldConfig) {
		this.fieldConfig = fieldConfig;
		this.itemFieldConfig = itemFieldConfig;
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

	public ItemFieldConfig getItemFieldConfig() {
		return itemFieldConfig;
	}

	public void setItemFieldConfig(ItemFieldConfig itemFieldConfig) {
		this.itemFieldConfig = itemFieldConfig;
	}
}
