package com.itemconfiguration.service.savestrategy.itemField;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.dto.ItemFieldCrudOperationsDto;
import com.itemconfiguration.service.FieldService;
import com.itemconfiguration.service.ItemService;
import com.itemconfiguration.utils.ItemUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

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
		List<ItemWithFieldsMap> itemsWithFieldsMap = getItemsWithFieldsMap(dto);
		if (CollectionUtils.isEmpty(itemsWithFieldsMap)) {
			return;
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
				if (isOriginalItemField(field, itemField)) {
					result.add(field);
				} else {
					addFieldForItem(itemWithFieldsMap, field, result);
				}
			}
		}
		return result;
	}

	protected boolean isOriginalItemField(Field field, Field itemField) {
		return itemField != null && itemField.getId().equals(field.getId());
	}

	protected abstract void addFieldForItem(ItemWithFieldsMap itemWithFieldsMap, Field field, List<Field> result);

	protected List<ItemWithFieldsMap> getItemsWithFieldsMap(ItemFieldCrudOperationsDto dto) {
		List<Item> result = itemService.findAllByItemNumbers(dto.getItemNumbers());
		return ItemUtils.convertToItemsWithFieldsMap(ItemUtils.filterByMultipleFields(result,
				dto.getItemFieldsCriteria().getMultipleFields()));
	}

}
