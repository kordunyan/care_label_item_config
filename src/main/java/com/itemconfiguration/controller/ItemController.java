package com.itemconfiguration.controller;

import com.itemconfiguration.converter.ItemConverter;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemPrintType;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.dto.CopyItemDto;
import com.itemconfiguration.dto.ItemNoMandatoryDataDto;
import com.itemconfiguration.dto.ItemWithoutItemFieldConfigsDto;
import com.itemconfiguration.dto.UpdateLocationDto;
import com.itemconfiguration.service.ItemService;
import com.itemconfiguration.service.savestrategy.item.ItemSaveStrategyProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemController {

	private final ItemService itemService;
	private final ItemSaveStrategyProvider itemSaveStrategyProvider;

	public ItemController(ItemService itemService, ItemSaveStrategyProvider itemSaveStrategyProvider) {
		this.itemService = itemService;
		this.itemSaveStrategyProvider = itemSaveStrategyProvider;
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
	public ResponseEntity<Void> copyItem(@RequestBody CopyItemDto dto) {
		itemSaveStrategyProvider.getSaveStrategy(dto).save(dto);
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

	@GetMapping("/printtypes")
	@Transactional
	public ResponseEntity<Void> updatePrintTypes() {

		List<Item> items = itemService.findAllByItemNumbers(new ArrayList<String>() {{
			add("022008-WN");
			add("022008-W-UFO_HERINGN");
			add("032431-BN");
			add("032431-WN");
			add("032463-BN");
			add("032463-WN");
			add("032466-BN");
			add("032466-WN");
			add("032471-BN");
			add("032471-WN");
			add("032478-BN");
			add("032478-WN");
			add("032480-BN");
			add("032480-WN");
			add("17633-WN");
			add("45579N");
			add("CALL0693N");
			add("CKJ-74141-BN");
			add("CKJ-74141-WN");
			add("CKJ-74142-BN");
			add("CKJ-74142-WN");
			add("CL-900165-BLKN");
			add("CL-900165-WHTN");
			add("CL-900170-BLKN");
			add("CL-900170-WHTN");
			add("CL-900171-BLKN");
			add("CL-900171-WHTN");
			add("CL-900172-BLKN");
			add("CL-900172-WHTN");
			add("CL-900185-BLKN");
			add("CL-900185-WHTN");
			add("CL-900186-BLKN");
			add("CL-900186-WHTN");
			add("CO56-THN");
			add("DKLB24N");
			add("IPL-105ETLN");
			add("IPL-105TLN");
			add("LB030BN");
			add("LB076N");
			add("LB089N");
			add("LB14SP-ARN");
			add("LB15-GENN");
			add("LB17-GENN");
			add("OEKO-TEXN");
			add("PCVARN");
			add("PCVAR-ESP-CKN");
			add("PCVARFRN");
			add("PCVARKC-ENN");
			add("PCVARKC-FRN");
			add("PCVARKC-SPN");
			add("PCVARSPN");
			add("PCVAR-STN");
			add("PL33-CKN");
			add("PL58-THN");
			add("PR-100005-BLKN");
			add("PR-100005-WHTN");
			add("PR-100006-BLKN");
			add("PR-100006-WHTN");
			add("PR-100007-BLKN");
			add("PR-100007-WHTN");
			add("PR-100008-BLKN");
			add("PR-100008-WHTN");
			add("PR-100010");
			add("PR-100011");
			add("S32");
			add("THG-71717-N");
			add("THG-71717-W");
			add("THG-71987-N");
			add("THG-71987-W");
			add("THG-73713-N");
			add("THG-73713-W");
			add("THG-74719-O");
			add("THWL0014");
			add("THWL0015");
			add("THWL0017");
			add("THWL0018");

		}});


		Map<String, List<Item>> itemsByNumber = new HashMap<>();

		for (Item item : items) {
			ItemWithFieldsMap itemWithFieldsMap = new ItemWithFieldsMap(item);
			String number = itemWithFieldsMap.getFieldValue("D2COMM_ITEM_NUMBER");
			itemsByNumber.computeIfAbsent(number, (empty) -> new ArrayList<>()).add(item);
		}


		for (String numbers : itemsByNumber.keySet()) {


			Optional<Item> first = itemsByNumber.get(numbers)
					.stream()
					.filter(item -> CollectionUtils.isNotEmpty(item.getItemPrintTypes()))
					.findFirst();

			if (first.isPresent()) {
				itemsByNumber.get(numbers)
						.stream()
						.filter(item -> CollectionUtils.isEmpty(item.getItemPrintTypes()))
						.forEach(item -> {
							copyItemPrintTypes(item, first.get());
						});
			} else {
				System.out.println("No print types for " + numbers);
			}


		}



		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	protected void copyItemPrintTypes(Item newItem, Item copyItem) {
		if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(copyItem.getItemPrintTypes())) {
			copyItem.getItemPrintTypes()
					.stream()
					.map(ItemPrintType::new)
					.forEach(newItem::addItemPrintType);
		}
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
