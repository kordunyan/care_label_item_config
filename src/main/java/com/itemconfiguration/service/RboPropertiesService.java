package com.itemconfiguration.service;

import com.itemconfiguration.config.TenantContext;
import com.itemconfiguration.config.properties.MultiTenantRboProperties;
import com.itemconfiguration.config.properties.RboProperty;
import com.itemconfiguration.converter.RboPropertyConverter;
import com.itemconfiguration.dto.RboDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RboPropertiesService {

	private MultiTenantRboProperties rbosProperties;
	private Map<String, RboProperty> rboPropertyByCode = new HashMap<>();

	public RboPropertiesService(MultiTenantRboProperties rbosProperties) {
		this.rbosProperties = rbosProperties;
	}

	public List<RboDto> getAvailableRbos() {
		return rbosProperties.getRbo()
				.stream()
				.map(RboPropertyConverter::convert)
				.sorted((rbo1, rbo2) -> rbo1.getName().compareToIgnoreCase(rbo2.getName()))
				.collect(Collectors.toList());
	}

	public Set<String> getMultipleFields() {
		String rboCode = (String) TenantContext.getCurrentTenant();
		if (rboPropertyByCode.containsKey(rboCode)) {
			return this.rboPropertyByCode.get(rboCode).getMultifields();
		}
		return Collections.emptySet();
	}

	@PostConstruct
	public void afterPropertiesSet() {
		buildRboPropertyMap();
	}

	private void buildRboPropertyMap() {
		rboPropertyByCode = rbosProperties.getRbo()
				.stream()
				.collect(Collectors.toMap(RboProperty::getCode, rboProperty -> rboProperty));
	}

}
