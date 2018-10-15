package com.itemconfiguration.controller;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.ItemCrudOperationsDto;
import com.itemconfiguration.dto.SaveConfigDto;
import com.itemconfiguration.exception.SaveItemFieldConfigException;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.deletestrategy.itemfieldconfig.DeleteItemFieldConfigStrategy;
import com.itemconfiguration.service.savestrategy.itemfieldconfig.ItemFieldConfigSaveStrategyProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/item_field_config")
public class ItemFieldConfigController {

	private ItemFieldConfigSaveStrategyProvider saveStrategyProvider;
	private ItemFieldConfigService itemFieldConfigService;

	@Resource(name = "original")
	private DeleteItemFieldConfigStrategy forOriginalItemDeleteStrategy;

	@Resource(name = "forAll")
	private DeleteItemFieldConfigStrategy forAllItemsDeleteStrategy;



	public ItemFieldConfigController(ItemFieldConfigSaveStrategyProvider saveStrategyProvider,
			ItemFieldConfigService itemFieldConfigService) {
		this.saveStrategyProvider = saveStrategyProvider;
		this.itemFieldConfigService = itemFieldConfigService;
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Void> save(@RequestBody SaveConfigDto saveConfigDto) {
		try {
			saveStrategyProvider.getSaveStrategy(saveConfigDto).save(saveConfigDto);
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

	@GetMapping("/instructions/{itemId}")
	public List<ItemFieldConfig> getInstructionsByItemId(@PathVariable("itemId") Long itemId) {
		return itemFieldConfigService.getInstructionsByItemId(itemId);
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
