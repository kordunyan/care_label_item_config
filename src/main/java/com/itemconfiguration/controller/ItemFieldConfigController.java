package com.itemconfiguration.controller;

import com.itemconfiguration.dto.SaveItemFieldConfigDto;
import com.itemconfiguration.exception.SaveItemFieldConfigException;
import com.itemconfiguration.service.savestrategy.itemfieldconfig.ItemFieldConfigSaveStrategyProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item_field_config")
public class ItemFieldConfigController {

	private ItemFieldConfigSaveStrategyProvider saveStrategyProvider;

	public ItemFieldConfigController(ItemFieldConfigSaveStrategyProvider saveStrategyProvider) {
		this.saveStrategyProvider = saveStrategyProvider;
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Void> save(@RequestBody SaveItemFieldConfigDto saveItemFieldConfigDto) {
		try {
			saveStrategyProvider.getSaveStrategy(saveItemFieldConfigDto).save(saveItemFieldConfigDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SaveItemFieldConfigException e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}