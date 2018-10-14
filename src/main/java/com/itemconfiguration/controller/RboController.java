package com.itemconfiguration.controller;

import com.itemconfiguration.config.properties.MultiTenantRboProperties;
import com.itemconfiguration.converter.RboPropertyConverter;
import com.itemconfiguration.dto.RboDto;
import com.itemconfiguration.service.RboPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rbo")
public class RboController {

	private RboPropertiesService rboPropertiesService;

	public RboController(RboPropertiesService rboPropertiesService) {
		this.rboPropertiesService = rboPropertiesService;
	}

	@GetMapping("/rbos")
	public List<RboDto> getAvailableRbos() {
		return rboPropertiesService.getAvailableRbos();
	}

	@GetMapping("/multiple-fields")
	public Set<String> getRboMultipleFields() {
		return rboPropertiesService.getMultipleFields();
	}

}
