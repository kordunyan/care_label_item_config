package com.itemconfiguration.converter;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.dto.ItemNoMandatoryDataDto;
import org.apache.commons.collections4.CollectionUtils;

public class ItemConverter {
	public static ItemNoMandatoryDataDto convertNoMandatory(Item item) {
		ItemNoMandatoryDataDto result = new ItemNoMandatoryDataDto();
		result.setId(item.getId());
		result.setIpps(item.isIpps());
		result.setSb(item.isSb());
		result.setFieldSet(item.getFieldSet());
		result.setFields(item.getFields());
		if (item.getItemFieldConfigs() != null && CollectionUtils.isNotEmpty(item.getItemFieldConfigs())) {
			item.getItemFieldConfigs().forEach(itemFieldConfig -> {
				result.addItemFiieldConfig(ItemFieldConfigConverter.convertToNoMandatory(itemFieldConfig));
			});
		}
		return result;
	}
}
