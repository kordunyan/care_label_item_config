package com.itemconfiguration.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "spring.multitenancy")
public class MultiTenantRboProperties {
	@NestedConfigurationProperty
	private List<RboProperty> rbo = new ArrayList<>();

	public List<RboProperty> getRbo() {
		return rbo;
	}

	public void setRbo(List<RboProperty> rbo) {
		this.rbo = rbo;
	}
}
