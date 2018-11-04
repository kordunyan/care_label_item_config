package com.itemconfiguration.service.deletestrategy.mandatory;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.service.ItemFieldConfigService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractMandatoryDataDeleteForItemNumbersStrategy implements MandatoryDataDeleteStrategy {

	private final ItemFieldConfigService itemFieldConfigService;

	public AbstractMandatoryDataDeleteForItemNumbersStrategy(ItemFieldConfigService itemFieldConfigService) {
		this.itemFieldConfigService = itemFieldConfigService;
	}

	public ItemFieldConfigService getItemFieldConfigService() {
		return itemFieldConfigService;
	}

	@Override
	public void delete(DeleteMandatoryDataDto dto) {
		Map<String, List<ItemFieldConfig>> allItemFieldConfigs = itemFieldConfigService.getByFieldConfigNamesAndItemNumbersMap(
				getItemFieldConfigNames(dto), dto.getItemNumbers());
		deleteMandatoryData(dto, allItemFieldConfigs);
	}

	protected abstract void deleteMandatoryData(DeleteMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs);

	protected abstract List<String> getItemFieldConfigNames(DeleteMandatoryDataDto dto);
}
