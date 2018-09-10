package com.itemconfiguration.controller;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.dto.FieldForAllItemsDto;
import com.itemconfiguration.dto.ItemFieldCrudOperationsDto;
import com.itemconfiguration.service.FieldService;
import com.itemconfiguration.service.savestrategy.itemField.ItemFieldSaveStrategyProvider;
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
	private ItemFieldSaveStrategyProvider itemFieldSaveStrategyProvider;

	public FieldController(FieldService fieldService, ItemFieldSaveStrategyProvider itemFieldSaveStrategyProvider) {
		this.fieldService = fieldService;
		this.itemFieldSaveStrategyProvider = itemFieldSaveStrategyProvider;
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

	@PostMapping("/saveall")
	public ResponseEntity<Void> newSaveAll(@RequestBody ItemFieldCrudOperationsDto dto) {
		itemFieldSaveStrategyProvider.getStrategy(dto).save(dto);
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
