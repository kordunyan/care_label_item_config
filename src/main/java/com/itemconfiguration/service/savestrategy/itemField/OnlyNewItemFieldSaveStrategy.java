package com.itemconfiguration.service.savestrategy.itemField;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.dto.ItemFieldCrudOperationsDto;
import com.itemconfiguration.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("onlyNewFields")
public class OnlyNewItemFieldSaveStrategy extends AbstractItemFieldSaveStrategy {

	public OnlyNewItemFieldSaveStrategy(ItemService itemService) {
		super(itemService);
	}

	@Override
	protected void addFieldForItem(ItemWithFieldsMap itemWithFieldsMap, Field field, List<Field> result) {
		if (itemWithFieldsMap.containsField(field.getFieldConfigName()))	{
			return;
		}
		result.add(new Field(field.getFieldConfigName(), field.getValue(), itemWithFieldsMap.getItem().getFieldSet()));
	}
}