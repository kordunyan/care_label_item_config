package com.itemconfiguration.controller;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.CopyItemDto;
import com.itemconfiguration.dto.ItemWithoutItemFieldConfigsDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/item")
public class ItemController {

	private ItemService itemService;
	protected ItemFieldConfigService itemFieldConfigService;

	public ItemController(ItemService itemService, ItemFieldConfigService itemFieldConfigService) {
		this.itemService = itemService;
		this.itemFieldConfigService = itemFieldConfigService;
	}

	@GetMapping("/new")
	public Item createNewItem() {
		final String ITEM_NUMBER = "2149324";
		Item item = null;
		for (int i = 0; i < 2; i++) {
			List<Field> fields = new ArrayList<>();
			fields.add(new Field("BRAND", "The Underwear Group", null));
			fields.add(new Field("SEASON", "SP18" + i, null));
			fields.add(new Field("D2COMM_ITEM_NUMBER", ITEM_NUMBER, null));

			item = new Item(true, true, null);
			item.setFields(fields);

			List<ItemFieldConfig> itemFieldConfigs = new ArrayList<>();
			itemFieldConfigs.add(new ItemFieldConfig("SIZE", true, true, true, true, null, "{'searchByRegexFields': [{fieldName:'SIZE_SPECIAL_HANDLEING', regex:'(?i)^.*B.*$'}]}", true, null, item));
			itemFieldConfigs.add(new ItemFieldConfig("COO", true, true, true, true, null, null, true, null, item));
			itemFieldConfigs.add(new ItemFieldConfig("EOD", true, true, true, true, null, null, true, null, item));
			itemFieldConfigs.add(new ItemFieldConfig("STYLE", true, true, true, true, null, null, true, null, item));
			item.setItemFieldConfigs(itemFieldConfigs);
			itemService.save(item);
		}

		//this.itemService.deleteByItemNumber(ITEM_NUMBER);

		return item;
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
	public int updateLocationEnablemendForAll(@RequestBody Item item) {
		return itemService.updateLocationEnablemendForAll(item);
	}

	@PostMapping("/save-all")
	public ResponseEntity<Void> saveAll(@RequestBody List<Item> items) {
		itemService.saveAll(items);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/copy-all")
	public ResponseEntity<Void> copyItem(@RequestBody CopyItemDto copyItemDto) {
		Optional<Item> optionalCopyItem = itemService.getById(copyItemDto.getCopyItemId());
		optionalCopyItem.ifPresent(copyItem -> {
			List<ItemFieldConfig> copyItemFieldConfigs = new ArrayList<>();
			for (Item newItem : copyItemDto.getItems()) {
				 copyItem.getItemFieldConfigs().stream()
					.map(ItemFieldConfig::copyWithoutIdAndItem)
					.peek(itemFieldConfig -> itemFieldConfig.setItem(newItem))
					.forEach(copyItemFieldConfigs::add);
			}
			itemService.saveAll(copyItemDto.getItems());
			itemFieldConfigService.saveAll(copyItemFieldConfigs);
		});
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

	@DeleteMapping("/number/{itemNumber}")
	public ResponseEntity<Void> deleteByItemNumber(@PathVariable("itemNumber") String itemNumber) {
		this.itemService.deleteByItemNumber(itemNumber);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
