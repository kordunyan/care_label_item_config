package com.itemconfiguration.config.properties;

import java.util.*;

public class RboProperty {
	private String name;
	private String code;
	private String url;
	private Set<String> multifields = new LinkedHashSet<>();

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

	public Set<String> getMultifields() {
		return multifields;
	}

	public void setMultifields(Set<String> multifields) {
		this.multifields = multifields;
	}
}
