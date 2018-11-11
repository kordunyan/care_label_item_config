package com.itemconfiguration.service;

import com.itemconfiguration.dao.ItemDAO;
import com.itemconfiguration.domain.AppFields;
import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.FieldSet;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.itemconfiguration.dto.CopyItemDto;
import com.itemconfiguration.dto.UpdateLocationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

	private final ItemDAO itemDAO;
	private final FieldSetService fieldSetService;
	private final FieldService fieldService;
	private final ItemFieldConfigService itemFieldConfigService;

	public ItemService(ItemDAO itemDAO, FieldSetService fieldSetService, FieldService fieldDAO, ItemFieldConfigService itemFieldConfigService) {
		this.itemDAO = itemDAO;
		this.fieldSetService = fieldSetService;
		this.fieldService = fieldDAO;
		this.itemFieldConfigService = itemFieldConfigService;
	}

	public int updateLocationEnablemend(Item item) {
		return this.itemDAO.updateLocationEnablemend(item.isIpps(), item.isSb(), item.getId());
	}

	@Transactional
	public void updateLocationEnablemendForAll(UpdateLocationDto dto) {
		if (CollectionUtils.isEmpty(dto.getItemNumbers())) {
			throw new IllegalArgumentException("[itemNumbers] should not be empty");
		}
		List<Item> items = this.findAllByItemNumbers(dto.getItemNumbers());
		items.forEach(item -> {
			item.setIpps(dto.isIpps());
			item.setSb(dto.isSb());
		});
		itemDAO.saveAll(items);
	}

	@Transactional
	public void save(Item item) {
		FieldSet fieldSet = new FieldSet();
		item.setFieldSet(fieldSet);
		fieldSetService.save(item.getFieldSet());
		if (!CollectionUtils.isEmpty(item.getFields())) {
			item.getFields().forEach(field -> field.setFieldSet(fieldSet));
			fieldService.saveAll(item.getFields());
		}
		itemDAO.save(item);
		itemFieldConfigService.saveAll(item.getItemFieldConfigs());
	}

	@Transactional
	public void saveWithItemFieldConfigCopy(CopyItemDto copyItemDto) {
		Optional<Item> optionalCopyItem = getById(copyItemDto.getCopyItemId());
		optionalCopyItem.ifPresent(copyItem -> {
			List<ItemFieldConfig> copyItemFieldConfigs = new ArrayList<>();
			for (Item newItem : copyItemDto.getItems()) {
				copyItem.getItemFieldConfigs().stream()
						.map(ItemFieldConfig::copyWithoutIdAndItem)
						.peek(itemFieldConfig -> itemFieldConfig.setItem(newItem))
						.forEach(copyItemFieldConfigs::add);
			}
			saveAll(copyItemDto.getItems());
			itemFieldConfigService.saveAll(copyItemFieldConfigs);
		});
	}

	@Transactional
	public void saveAll(List<Item> items) {
		List<FieldSet> fieldSets = new ArrayList<>();
		List<Field> fields = new ArrayList<>();
		for (Item item : items) {
			FieldSet fieldSet = new FieldSet();
			item.setFieldSet(fieldSet);
			fieldSets.add(fieldSet);
			if (!CollectionUtils.isEmpty(item.getFields())) {
				item.getFields().forEach(field -> {
					field.setFieldSet(fieldSet);
					fields.add(field);
				});
			}
		}
		fieldSetService.saveAll(fieldSets);
		fieldService.saveAll(fields);
		itemDAO.saveAll(items);
	}

	public List<ItemWithItemFieldConfigsMap> getAllItemsWithFieldConfigMapByItemNumbers(List<String> itemNumbers) {
		return convertToItemWithItemFieldConfigsMap(findAllByItemNumbers(itemNumbers));
	}

	public List<ItemWithFieldsMap> findItemsFithFieldMapByItemNumbers(List<String> itemNumbers) {
		return conertToItemWithFieldsMap(findAllByItemNumbers(itemNumbers));
	}

	private List<ItemWithFieldsMap> conertToItemWithFieldsMap(List<Item> items) {
		return items.stream()
				.map(ItemWithFieldsMap::new)
				.collect(Collectors.toList());
	}

	private List<ItemWithItemFieldConfigsMap> convertToItemWithItemFieldConfigsMap(List<Item> items) {
		return items.stream()
				.map(ItemWithItemFieldConfigsMap::new)
				.collect(Collectors.toList());
	}

	public List<String> getAllItemNumbers() {
		return fieldService.findByFieldConfigName(AppFields.D2COMM_ITEM_NUMBER);
	}

	public List<Item> findByItemNumber(String itemNumber) {
		return itemDAO.findAllByField(AppFields.D2COMM_ITEM_NUMBER, itemNumber);
	}

	public List<Item> findAllByItemNumbers(List<String> itemNumbers) {
		return itemDAO.findAllByFieldsLight(AppFields.D2COMM_ITEM_NUMBER, itemNumbers);
	}

	public List<Item> getAll() {
		return (List<Item>) itemDAO.findAll();
	}

	public Optional<Item> getById(Long id) {
		return itemDAO.findById(id);
	}

	@Transactional
	public void delete(Item item) {
		fieldService.deleteByFieldSet(item.getFieldSet());
		itemDAO.delete(item);
	}

	@Transactional
	public void deleteByItemNumber(String itemNumber) {
		List<Item> items = findByItemNumber(itemNumber);
		List<FieldSet> fieldSets = items.stream().map(Item::getFieldSet).collect(Collectors.toList());
		fieldService.deleteByFieldSets(fieldSets);
		this.itemDAO.deleteAll(items);
	}
}
