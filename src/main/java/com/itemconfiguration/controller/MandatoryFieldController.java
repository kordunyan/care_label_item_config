package com.itemconfiguration.controller;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.service.FieldConfigService;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.MandatoryFieldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/mandatory/field")
public class MandatoryFieldController {

	private MandatoryFieldService mandatoryFieldService;
	private ItemFieldConfigService itemFieldConfigService;
	private FieldConfigService fieldConfigService;

	public MandatoryFieldController(MandatoryFieldService mandatoryFieldService, ItemFieldConfigService itemFieldConfigService,
									FieldConfigService fieldConfigService) {
		this.mandatoryFieldService = mandatoryFieldService;
		this.itemFieldConfigService = itemFieldConfigService;
		this.fieldConfigService = fieldConfigService;
	}

	@GetMapping("/test")
	public ItemFieldConfig testSaveNewMandatoryField() {
		ItemFieldConfig itemFieldConfig = itemFieldConfigService.getById(1L);
		FieldConfig fieldConfig = fieldConfigService.findByName("BRAND").orElse(null);

		MandatoryField mandatoryField = new MandatoryField(itemFieldConfig, fieldConfig);

		mandatoryFieldService.save(mandatoryField);

		return itemFieldConfig;
	}
}
