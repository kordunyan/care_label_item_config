package com.itemconfiguration.converter;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.ItemFieldConfigNoMandatoryDataDto;

public class ItemFieldConfigConverter {

	public static ItemFieldConfigNoMandatoryDataDto convertToNoMandatory(ItemFieldConfig itemFieldConfig) {
		ItemFieldConfigNoMandatoryDataDto result = new ItemFieldConfigNoMandatoryDataDto();
		result.setId(itemFieldConfig.getId());
		result.setFieldConfigName(itemFieldConfig.getFieldConfigName());
		result.setActive(itemFieldConfig.isActive());
		result.setRequired(itemFieldConfig.isRequired());
		result.setEditable(itemFieldConfig.isEditable());
		result.setStoreLastUserInput(itemFieldConfig.isStoreLastUserInput());
		result.setPredefinedValue(itemFieldConfig.getPredefinedValue());
		result.setFilterRegex(itemFieldConfig.getFilterRegex());
		result.setCanAddLater(itemFieldConfig.isCanAddLater());
		result.setDataSourceName(itemFieldConfig.getDataSourceName());
		return result;
	}

}
