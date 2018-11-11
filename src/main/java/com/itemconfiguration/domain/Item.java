package com.itemconfiguration.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
public class Item implements Serializable {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "is_ipps")
	private boolean ipps;

	@Column(name = "is_sb")
	private boolean sb;

	@OneToOne()
	@JoinColumn(name = "field_set_id")
	private FieldSet fieldSet;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "field_set_id", referencedColumnName = "field_set_id")
	@OrderBy("fieldConfigName ASC")
	private List<Field> fields = new ArrayList<>();

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemFieldConfig> itemFieldConfigs = new ArrayList<>();

	public Item() {
	}

	public Item(boolean ipps, boolean sb, FieldSet fieldSet) {
		this.ipps = ipps;
		this.sb = sb;
		this.fieldSet = fieldSet;
		this.fields = fields;
		this.itemFieldConfigs = itemFieldConfigs;
	}

	public void setIpps(boolean ipps) {
		this.ipps = ipps;
	}

	public boolean isIpps() {
		return ipps;
	}

	public boolean isSb() {
		return sb;
	}

	public void setSb(boolean sb) {
		this.sb = sb;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<ItemFieldConfig> getItemFieldConfigs() {
		return itemFieldConfigs;
	}

	public void setItemFieldConfigs(List<ItemFieldConfig> itemFieldConfigs) {
		this.itemFieldConfigs = itemFieldConfigs;
	}

	public void deleteItemFieldConfig(ItemFieldConfig itemFieldConfig) {
		this.itemFieldConfigs.remove(itemFieldConfig);
	}
}
