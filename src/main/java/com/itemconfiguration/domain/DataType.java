package com.itemconfiguration.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DataType {
	@Id
	private String name;

	private boolean isInstruction;

	public DataType() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInstruction() {
		return isInstruction;
	}

	public void setInstruction(boolean instruction) {
		isInstruction = instruction;
	}
}
