package com.itemconfiguration.controller;

import com.itemconfiguration.domain.FieldSet;
import com.itemconfiguration.service.FieldSetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/field_set")
public class FieldSetController {

	private FieldSetService fieldSetService;

	public FieldSetController(FieldSetService fieldSetService) {
		this.fieldSetService = fieldSetService;
	}

	@GetMapping("/new")
	public FieldSet insertNewFieldSet() {
		FieldSet result = new FieldSet();
		fieldSetService.save(result);
		return result;
	}
}
