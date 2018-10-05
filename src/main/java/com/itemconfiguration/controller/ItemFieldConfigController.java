package com.itemconfiguration.controller;

import com.itemconfiguration.dto.ItemCrudOperationsDto;
import com.itemconfiguration.dto.SaveItemFieldConfigDto;
import com.itemconfiguration.exception.SaveItemFieldConfigException;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.deletestrategy.itemfieldconfig.DeleteItemFieldConfigStrategy;
import com.itemconfiguration.service.savestrategy.itemfieldconfig.ItemFieldConfigSaveStrategyProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/item_field_config")
public class ItemFieldConfigController {

	private ItemFieldConfigSaveStrategyProvider saveStrategyProvider;

	@Resource(name = "original")
	private DeleteItemFieldConfigStrategy forOriginalItemDeleteStrategy;

	@Resource(name = "forAll")
	private DeleteItemFieldConfigStrategy forAllItemsDeleteStrategy;



	public ItemFieldConfigController(ItemFieldConfigSaveStrategyProvider saveStrategyProvider,
			ItemFieldConfigService itemFieldConfigService) {
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

	@PostMapping(value = "/delete")
	public ResponseEntity<Void> delete(@RequestBody ItemCrudOperationsDto crudOperationsDto) {
		try {
			forOriginalItemDeleteStrategy.delete(crudOperationsDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SaveItemFieldConfigException e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "deleteForAll")
	public ResponseEntity<Void> deleteForAll(@RequestBody ItemCrudOperationsDto crudOperationsDto) {
		try {
			forAllItemsDeleteStrategy.delete(crudOperationsDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SaveItemFieldConfigException e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
