package com.itemconfiguration.service.savestrategy.mandatory;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractMandatoryDataSaveForItemNumbersStrategy implements MandatoryDataSaveStrategy {

	private final ItemFieldConfigService itemFieldConfigService;

	public AbstractMandatoryDataSaveForItemNumbersStrategy(ItemFieldConfigService itemFieldConfigService) {
		this.itemFieldConfigService = itemFieldConfigService;
	}

	@Override
	public List<ItemFieldConfig> save(SaveMandatoryDataDto dto) {
		if (CollectionUtils.isEmpty(dto.getItemFieldConfigs())) {
			throw new IllegalArgumentException("[itemFieldConfigs] should not be empty");
		}
		if (CollectionUtils.isEmpty(dto.getItemNumbers())) {
			throw new IllegalArgumentException("[itemNumbers] should not be empty");
		}
		Map<String, List<ItemFieldConfig>> allItemFieldConfigs = itemFieldConfigService.getByFieldConfigNamesAndItemNumbersMap(
				getItemFieldConfigNames(dto.getItemFieldConfigs()), dto.getItemNumbers());
		saveNewData(dto, allItemFieldConfigs);
		return dto.getItemFieldConfigs();
	}

	private List<String> getItemFieldConfigNames(List<ItemFieldConfig> itemFieldConfigs) {
		return itemFieldConfigs.stream()
				.map(itemFieldConfig -> itemFieldConfig.getFieldConfig().getName())
				.collect(Collectors.toList());
	}

	public ItemFieldConfigService getItemFieldConfigService() {
		return itemFieldConfigService;
	}

	protected abstract void saveNewData(SaveMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs);
}
