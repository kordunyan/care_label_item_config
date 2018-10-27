package com.itemconfiguration.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
		name = "item_field_config",
		uniqueConstraints = @UniqueConstraint(
				name = "item_field_config_unq_key",
				columnNames = {"item_id", "field_config_name"}
		)
)
@JsonIgnoreProperties(value = {"item"})
public class ItemFieldConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "field_config_name")
	private FieldConfig fieldConfig;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_required")
	private boolean isRequired;

	@Column(name = "is_editable")
	private boolean isEditable;

	@Column(name = "store_last_user_input")
	private boolean storeLastUserInput;

	@Column(name = "predefined_value")
	private String predefinedValue;

	@Column(name = "filter_regex", columnDefinition = "text")
	private String filterRegex;

	@Column(name = "can_add_later")
	private boolean canAddLater;

	@Column(name = "data_source_name")
	private String dataSourceName;

	@ManyToOne()
	@JoinColumn(name = "item_id")
	private Item item;

	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "itemFieldConfig",
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	private List<MandatoryField> mandatoryFields = new ArrayList<>();

	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "itemFieldConfig",
			fetch = FetchType.LAZY
	)
	private List<MandatoryTranslation> mandatoryTranslations = new ArrayList<>();

	public static ItemFieldConfig copyWithoutIdAndItem(ItemFieldConfig src) {
		ItemFieldConfig copy = new ItemFieldConfig();
		copy.isActive = src.isActive;
		copy.isRequired = src.isRequired;
		copy.isEditable = src.isEditable;
		copy.storeLastUserInput = src.storeLastUserInput;
		copy.predefinedValue = src.predefinedValue;
		copy.filterRegex = src.filterRegex;
		copy.canAddLater = src.canAddLater;
		copy.dataSourceName = src.dataSourceName;
		copy.fieldConfig = src.fieldConfig;
		return copy;
	}

	public ItemFieldConfig() {
	}

	public ItemFieldConfig(FieldConfig fieldConfig, boolean isActive, boolean isRequired, boolean isEditable, boolean storeLastUserInput, String predefinedValue, String filterRegex, boolean canAddLater, String dataSourceName, Item item) {
		this.fieldConfig = fieldConfig;
		this.isActive = isActive;
		this.isRequired = isRequired;
		this.isEditable = isEditable;
		this.storeLastUserInput = storeLastUserInput;
		this.predefinedValue = predefinedValue;
		this.filterRegex = filterRegex;
		this.canAddLater = canAddLater;
		this.dataSourceName = dataSourceName;
		this.item = item;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<MandatoryField> getMandatoryFields() {
		return mandatoryFields;
	}

	public void setMandatoryFields(List<MandatoryField> mandatoryFields) {
		this.mandatoryFields = mandatoryFields;
	}

	public List<MandatoryTranslation> getMandatoryTranslations() {
		return mandatoryTranslations;
	}

	public void setMandatoryTranslations(List<MandatoryTranslation> mandatoryTranslations) {
		this.mandatoryTranslations = mandatoryTranslations;
	}
}
