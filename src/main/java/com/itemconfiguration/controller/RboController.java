package com.itemconfiguration.controller;

import com.itemconfiguration.config.properties.MultiTenantRboProperties;
import com.itemconfiguration.converter.RboPropertyConverter;
import com.itemconfiguration.dto.RboDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rbo")
public class RboController {

	private MultiTenantRboProperties rbosProperties;

	public RboController(MultiTenantRboProperties rbosProperties) {
		this.rbosProperties = rbosProperties;
	}

	@GetMapping("/rbos")
	public List<RboDto> getAvailableRbos() {
		return rbosProperties.getRbo()
				.stream()
				.map(RboPropertyConverter::convert)
				.sorted((rbo1, rbo2) -> rbo1.getName().compareToIgnoreCase(rbo2.getName()))
				.collect(Collectors.toList());
	}

}
