package com.itemconfiguration.domain.wrapper;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.Item;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

public class ItemWithFieldsMap {

	private Item item;

	private Map<String, Field> fieldsMap = new HashMap<>();

	public ItemWithFieldsMap(Item item) {
		this.item = item;
		this.initFieldsMap();
	}

	private void initFieldsMap() {
		if (this.item == null || CollectionUtils.isEmpty(item.getFields())) {
			return;
		}
		item.getFields().forEach(field -> fieldsMap.put(field.getFieldConfigName(), field));
	}

	public Item getItem() {
		return item;
	}

	public Field getField(String fieldConfigName) {
		return fieldsMap.get(fieldConfigName);
	}

	public String getFieldValue(String fieldConfigName) {
		if (containsField(fieldConfigName)) {
			return getField(fieldConfigName).getValue();
		}
		return null;
	}

	public boolean hasFields() {
		return !fieldsMap.isEmpty();
	}

	public boolean containsField(String fieldConfigName) {
		return fieldsMap.containsKey(fieldConfigName);
	}
}
