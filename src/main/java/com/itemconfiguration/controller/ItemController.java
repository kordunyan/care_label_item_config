package com.itemconfiguration.controller;

import com.itemconfiguration.converter.ItemConverter;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.dto.CopyItemDto;
import com.itemconfiguration.dto.ItemNoMandatoryDataDto;
import com.itemconfiguration.dto.ItemWithoutItemFieldConfigsDto;
import com.itemconfiguration.dto.UpdateLocationDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/item")
public class ItemController {

	private ItemService itemService;
	protected ItemFieldConfigService itemFieldConfigService;

	public ItemController(ItemService itemService, ItemFieldConfigService itemFieldConfigService) {
		this.itemService = itemService;
		this.itemFieldConfigService = itemFieldConfigService;
	}

	@PostMapping()
	public ResponseEntity<Void> saveNewItem(@RequestBody Item item) {
		itemService.save(item);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/update-location")
	public int updateLocationEnablemend(@RequestBody Item item) {
		return itemService.updateLocationEnablemend(item);
	}

	@PostMapping("/update-location-all")
	public  ResponseEntity<Void> updateLocationEnablemendForAll(@RequestBody UpdateLocationDto dto) {
		itemService.updateLocationEnablemendForAll(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/save-all")
	public ResponseEntity<Void> saveAll(@RequestBody List<Item> items) {
		itemService.saveAll(items);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/copy-all")
	public ResponseEntity<Void> copyItem(@RequestBody CopyItemDto copyItemDto) {
		this.itemService.saveWithItemFieldConfigCopy(copyItemDto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/number/{itemNumber}")
	public List<ItemWithoutItemFieldConfigsDto> getByItemNumber(@PathVariable("itemNumber") String itemNumber) {
		List<Item> items = itemService.findByItemNumber(itemNumber);
		return items.stream().map(ItemWithoutItemFieldConfigsDto::new).collect(Collectors.toList());
	}

	@GetMapping("/numbers")
	public List<String> getAllItemValues() {
		return itemService.getAllItemNumbers();
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody Item item) {
		itemService.delete(item);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public Item getById(@PathVariable("id") Long id) {
		return itemService.getById(id).orElse(null);
	}

	@GetMapping("/nomandatory/id/{id}")
	public ItemNoMandatoryDataDto getNoMandatoryById(@PathVariable("id") Long id) {
		Optional<Item> optionalItem = itemService.getById(id);
		return optionalItem
				.map(ItemConverter::convertNoMandatory)
				.orElse(null);
	}

	@GetMapping("/without-field-configs/id/{id}")
	public ItemWithoutItemFieldConfigsDto getWithoutFieldConfigsById(@PathVariable("id") Long id) {
		Optional<Item> optionalItem = itemService.getById(id);
		return optionalItem
				.map(ItemWithoutItemFieldConfigsDto::new)
				.orElse(null);
	}

	@DeleteMapping("/number/{itemNumber}")
	public ResponseEntity<Void> deleteByItemNumber(@PathVariable("itemNumber") String itemNumber) {
		this.itemService.deleteByItemNumber(itemNumber);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
