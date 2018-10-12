package com.itemconfiguration.config.properties;

import java.util.ArrayList;
import java.util.List;

public class RboProperty {
	private String name;
	private String code;
	private String url;
	private List<String> multifields = new ArrayList<>();

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getMultifields() {
		return multifields;
	}

	public void setMultifields(List<String> multifields) {
		this.multifields = multifields;
	}
}
