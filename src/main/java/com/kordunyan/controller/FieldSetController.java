package com.kordunyan.controller;

import com.kordunyan.domain.FieldSet;
import com.kordunyan.service.FieldSetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/field_set")
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
