package com.itemconfiguration.controller;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.dto.ItemFieldCrudOperationsDto;
import com.itemconfiguration.service.FieldService;
import com.itemconfiguration.service.deletestrategy.field.ForItemsFieldDeleteStrategy;
import com.itemconfiguration.service.savestrategy.itemField.ItemFieldSaveStrategyProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/field")
public class FieldController {

	private FieldService fieldService;
	private ItemFieldSaveStrategyProvider itemFieldSaveStrategyProvider;
	private ForItemsFieldDeleteStrategy  forItemsFieldDeleteStrategy;

	public FieldController(FieldService fieldService, ItemFieldSaveStrategyProvider itemFieldSaveStrategyProvider,
						   ForItemsFieldDeleteStrategy forItemsFieldDeleteStrategy) {
		this.fieldService = fieldService;
		this.itemFieldSaveStrategyProvider = itemFieldSaveStrategyProvider;
		this.forItemsFieldDeleteStrategy = forItemsFieldDeleteStrategy;
	}

	@PostMapping("/update")
	public Field update(@RequestBody Field field) {
		return this.fieldService.update(field);
	}

	@PostMapping("/save-all")
	public ResponseEntity<Void> saveAll(@RequestBody List<Field> fields) {
		this.fieldService.saveAll(fields);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/save-for-items")
	public ResponseEntity<Void> newSaveAll(@RequestBody ItemFieldCrudOperationsDto dto) {
		itemFieldSaveStrategyProvider.getStrategy(dto).save(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/delete-for-items")
	public ResponseEntity<Void> deleteForItems(@RequestBody ItemFieldCrudOperationsDto dto) {
		this.forItemsFieldDeleteStrategy.delete(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody Field field) {
		this.fieldService.delete(field);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
