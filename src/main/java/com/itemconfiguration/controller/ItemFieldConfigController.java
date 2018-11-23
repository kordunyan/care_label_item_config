package com.itemconfiguration.controller;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.ItemCrudOperationsDto;
import com.itemconfiguration.exception.SaveItemFieldConfigException;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.deletestrategy.itemfieldconfig.DeleteItemFieldConfigStrategy;
import com.itemconfiguration.service.savestrategy.itemfieldconfig.ItemFieldConfigSaveStrategyProvider;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Void> save(@RequestBody ItemCrudOperationsDto saveConfigDto) {
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
			if (crudOperationsDto.isForAll()) {
				forAllItemsDeleteStrategy.delete(crudOperationsDto);
			} else {
				forOriginalItemDeleteStrategy.delete(crudOperationsDto);
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (SaveItemFieldConfigException e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/instructions/{itemId}")
	public List<ItemFieldConfig> getInstructionsByItemId(@PathVariable("itemId") Long itemId) {
		return itemFieldConfigService.getInstructionsByItemId(itemId);
	}
}
