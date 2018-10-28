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

	public FieldConfig() {
	}

	public FieldConfig(String name, String type, String owner, boolean printable) {
		this.name = name;
		this.type = type;
		this.owner = owner;
		this.printable = printable;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FieldConfig that = (FieldConfig) o;

		if (printable != that.printable) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (type != null ? !type.equals(that.type) : that.type != null) return false;
		return owner != null ? owner.equals(that.owner) : that.owner == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (owner != null ? owner.hashCode() : 0);
		result = 31 * result + (printable ? 1 : 0);
		return result;
	}
}
