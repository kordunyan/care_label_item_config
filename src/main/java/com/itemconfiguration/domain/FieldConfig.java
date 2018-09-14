package com.itemconfiguration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "field_config")
public class FieldConfig {

	@Id
	private String name;

	private String type;

	@Column(columnDefinition = "text")
	private String owner;

	@Column(name = "is_printable")
	private boolean printable;

	@Column(name = "is_multiple")
	private boolean multiple;

	public FieldConfig() {
	}

	public FieldConfig(String name, String type, String owner, boolean printable, boolean multiple) {
		this.name = name;
		this.type = type;
		this.owner = owner;
		this.printable = printable;
		this.multiple = multiple;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean isPrintable() {
		return printable;
	}

	public void setPrintable(boolean printable) {
		this.printable = printable;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
}