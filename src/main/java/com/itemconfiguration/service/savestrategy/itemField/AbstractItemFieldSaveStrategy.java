package com.itemconfiguration.service.savestrategy.itemField;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.dto.ItemFieldCrudOperationsDto;
import com.itemconfiguration.service.FieldService;
import com.itemconfiguration.service.ItemService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractItemFieldSaveStrategy implements ItemFieldSaveStrategy {

	private ItemService itemService;
	private FieldService fieldService;

	public AbstractItemFieldSaveStrategy(ItemService itemService, FieldService fieldService) {
		this.itemService = itemService;
		this.fieldService = fieldService;
	}

	@Override
	public void save(ItemFieldCrudOperationsDto dto) {
		if (!dto.hasItemNumbers()) {
			throw new IllegalArgumentException("[itemNumbers] should no be empty");
		}
		if (!dto.hasFields()) {
			throw new IllegalArgumentException("[fields] should no be empty");
		}
		List<ItemWithFieldsMap> itemsWithFieldsMap = getItemsWithFieldsMap(dto.getItemNumbers());
		if (CollectionUtils.isEmpty(itemsWithFieldsMap)) {
			throw new IllegalStateException("There are any item with item numbers: " +
					StringUtils.join(dto.getItemNumbers(), ","));
		}
		List<Field> fieldsForSave = updateFieldsForItems(dto, itemsWithFieldsMap);
		this.fieldService.saveAll(fieldsForSave);
	}

	protected List<Field> updateFieldsForItems(ItemFieldCrudOperationsDto dto, List<ItemWithFieldsMap> itemsWithFieldsMap) {
		List<Field> result = new ArrayList<>();

		for (ItemWithFieldsMap itemWithFieldsMap : itemsWithFieldsMap) {
			for (Field field : dto.getFields()) {
				Field itemField = itemWithFieldsMap.getField(field.getFieldConfigName());
				//always should to update original field
				if (itemField != null && itemField.getId().equals(field.getId())) {
					result.add(field);
				} else {
					addFieldForItem(itemWithFieldsMap, field, result);
				}
			}
		}
		return result;
	}

	protected abstract void addFieldForItem(ItemWithFieldsMap itemWithFieldsMap, Field field, List<Field> result);

	protected List<ItemWithFieldsMap> getItemsWithFieldsMap(List<String> itemNumbers) {
		return itemService.findItemsFithFieldMapByItemNumbers(itemNumbers);
	}

}
