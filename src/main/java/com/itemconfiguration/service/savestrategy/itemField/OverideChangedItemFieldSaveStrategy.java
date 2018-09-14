package com.itemconfiguration.service.savestrategy.itemField;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.service.FieldService;
import com.itemconfiguration.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("overrideFields")
public class OverideChangedItemFieldSaveStrategy extends AbstractItemFieldSaveStrategy{

	public OverideChangedItemFieldSaveStrategy(ItemService itemService, FieldService fieldService) {
		super(itemService, fieldService);
	}

	@Override
	protected void addFieldForItem(ItemWithFieldsMap itemWithFieldsMap, Field field, List<Field> result) {
		Field itemField = itemWithFieldsMap.getField(field.getFieldConfigName());
		if (itemField != null) {
			itemField.setValue(field.getValue());
		} else {
			itemField = new Field(field);
			itemField.setFieldSet(itemWithFieldsMap.getItem().getFieldSet());
		}
		result.add(itemField);
	}
}
