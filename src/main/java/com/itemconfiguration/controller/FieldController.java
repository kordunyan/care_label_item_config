package com.itemconfiguration.controller;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.dto.FieldForAllItemsDto;
import com.itemconfiguration.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/field")
public class FieldController {

	private FieldService fieldService;

	public FieldController(FieldService fieldService) {
		this.fieldService = fieldService;
	}

	@PostMapping("/update")
	public Field update(@RequestBody Field field) {
		return this.fieldService.update(field);
	}

	@PostMapping("/updateAll")
	public long updateAllForItem(@RequestBody Field field) {
		return this.fieldService.updateAllForItem(field);
	}

	@PostMapping("/save-all")
	public ResponseEntity<Void> saveAll(@RequestBody List<Field> fields) {
		this.fieldService.saveAll(fields);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/save-for-all-items")
	public ResponseEntity<Void> saveNewFieldsForAllItems(@RequestBody List<FieldForAllItemsDto> newFieldsDto) {
		this.fieldService.saveNewFieldsForAllItems(newFieldsDto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/delete-for-all-items")
	public int deleteForAllItems(@RequestBody Field field) {
		return this.fieldService.deleteForAllItems(field);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody Field field) {
		this.fieldService.delete(field);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
