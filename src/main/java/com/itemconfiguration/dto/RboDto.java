package com.itemconfiguration.dto;

public class RboDto {
	private String name;
	private String code;

	public RboDto() {
	}

	public RboDto(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
