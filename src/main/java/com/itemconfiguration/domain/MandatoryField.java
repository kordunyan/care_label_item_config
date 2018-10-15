package com.itemconfiguration.domain;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.ItemFieldConfig;

import javax.persistence.*;

@Entity
public class MandatoryField {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(
			name = "field_config_name",
			foreignKey = @ForeignKey(name = "mandatory_field_field_condif_name_fkey"))
	private FieldConfig fieldConfig;

	@ManyToOne()
	@JoinColumn(name = "item_field_config_id", foreignKey = @ForeignKey(name = "mandatory_field_item_field_config_id_fkey"))
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
