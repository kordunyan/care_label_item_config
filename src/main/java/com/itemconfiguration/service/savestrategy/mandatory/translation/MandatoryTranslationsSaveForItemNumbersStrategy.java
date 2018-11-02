package com.itemconfiguration.service.savestrategy.mandatory.translation;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.MandatoryTranslationService;
import com.itemconfiguration.service.savestrategy.mandatory.MandatoryDataSaveStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("item-numbers-mandatory-save")
public class MandatoryTranslationsSaveForItemNumbersStrategy implements MandatoryDataSaveStrategy {
	private MandatoryTranslationService mandatoryTranslationService;
	private ItemFieldConfigService itemFieldConfigService;

	public MandatoryTranslationsSaveForItemNumbersStrategy(MandatoryTranslationService mandatoryTranslationService,
			ItemFieldConfigService itemFieldConfigService) {
		this.mandatoryTranslationService = mandatoryTranslationService;
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
		List<MandatoryTranslation> newMandatoryTranslations = createNewMandatoryTranslations(dto.getItemFieldConfigs(), allItemFieldConfigs);
		mandatoryTranslationService.saveAll(newMandatoryTranslations);
		return dto.getItemFieldConfigs();
	}

	private List<MandatoryTranslation> createNewMandatoryTranslations(List<ItemFieldConfig> originalItemFieldConfigs,
			Map<String, List<ItemFieldConfig>> itemFieldConfigsMap) {
		List<MandatoryTranslation> result = new ArrayList<>();
		for (ItemFieldConfig originalItemFieldConfig : originalItemFieldConfigs) {
			List<MandatoryTranslation> newMandatoryTranslations = originalItemFieldConfig.getNewMandatoryTranslations();
			List<ItemFieldConfig> itemNumbersFieldConfigs  = itemFieldConfigsMap.get(originalItemFieldConfig.getFieldConfig().getName());
			createMandatoryTranslationsForItemFieldConfigs(newMandatoryTranslations, itemNumbersFieldConfigs,
					originalItemFieldConfig.getId(), result);
			newMandatoryTranslations.forEach(mandatoryTranslation -> mandatoryTranslation.setItemFieldConfig(originalItemFieldConfig));
			result.addAll(newMandatoryTranslations);
		}
		return result;
	}

	private void createMandatoryTranslationsForItemFieldConfigs(List<MandatoryTranslation> newMandatoryTranslations, List<ItemFieldConfig> itemNumbersFieldConfigs,
			Long originItemFieldConfigId, List<MandatoryTranslation> result ) {
		for (ItemFieldConfig itemFieldConfig : itemNumbersFieldConfigs) {
			if (itemFieldConfig.getId().equals(originItemFieldConfigId)) {
				continue;
			}
			result.addAll(createNewMandatoryTranslationsForItemFieldConfig(newMandatoryTranslations, itemFieldConfig));
		}
	}

	private List<MandatoryTranslation> createNewMandatoryTranslationsForItemFieldConfig(List<MandatoryTranslation> newMandatoryTranslations,
			ItemFieldConfig itemFieldConfig) {
		List<MandatoryTranslation> result = new ArrayList<>();
		for (MandatoryTranslation newMandatoryTranslation : newMandatoryTranslations) {
			Optional<MandatoryTranslation> first = itemFieldConfig.getMandatoryTranslations()
					.stream()
					.filter(mandatoryTranslation -> mandatoryTranslation.getLanguage().equals(newMandatoryTranslation.getLanguage()))
					.findFirst();
			if (first.isPresent()) {
				continue;
			}
			result.add(new MandatoryTranslation(itemFieldConfig, newMandatoryTranslation.getLanguage()));
		}
		return result;
	}

	private List<String> getItemFieldConfigNames(List<ItemFieldConfig> itemFieldConfigs) {
		return itemFieldConfigs.stream()
				.map(itemFieldConfig -> itemFieldConfig.getFieldConfig().getName())
				.collect(Collectors.toList());
	}

}
