package com.itemconfiguration.service;

import com.itemconfiguration.dao.ItemDAO;
import com.itemconfiguration.domain.AppFields;
import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.FieldSet;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.domain.wrapper.ItemWithItemFieldConfigsMap;
import com.itemconfiguration.exception.SaveItemFieldConfigException;
import org.springframework.stereotype.Service;
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

	public int updateLocationEnablemendForAll(Item item) {
		return this.itemDAO.updateLocationEnablemendForAll(item.isIpps(), item.isSb(), item.getId());
	}

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
		List<Item> items = this.findAllByItemNumbers(itemNumbers);
		return convertToItemWithItemFieldConfigsMap(items);
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
		return itemDAO.findAllByFields(AppFields.D2COMM_ITEM_NUMBER, itemNumbers);
	}

	public List<Item> getAll() {
		return (List<Item>) itemDAO.findAll();
	}

	public Optional<Item> getById(Long id) {
		return itemDAO.findById(id);
	}

	public void delete(Item item) {
		itemDAO.delete(item);
	}

	public void deleteById(Long id) {
		itemDAO.deleteById(id);
	}

	public void deleteByItemNumber(String itemNumber) {
		List<Item> items = findByItemNumber(itemNumber);
		this.itemDAO.deleteAll(items);
	}
}
