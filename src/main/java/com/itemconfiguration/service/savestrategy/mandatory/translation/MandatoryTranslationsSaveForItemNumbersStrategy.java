package com.itemconfiguration.service.savestrategy.mandatory.translation;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.MandatoryTranslationService;
import com.itemconfiguration.service.savestrategy.mandatory.AbstractMandatoryDataSaveForItemNumbersStrategy;
import com.itemconfiguration.service.savestrategy.mandatory.MandatoryDataSaveStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("item-numbers-mandatory-save")
public class MandatoryTranslationsSaveForItemNumbersStrategy extends AbstractMandatoryDataSaveForItemNumbersStrategy {
	private MandatoryTranslationService mandatoryTranslationService;

	public MandatoryTranslationsSaveForItemNumbersStrategy(MandatoryTranslationService mandatoryTranslationService,
			ItemFieldConfigService itemFieldConfigService) {
		super(itemFieldConfigService);
		this.mandatoryTranslationService = mandatoryTranslationService;
	}

	@Override
	protected void saveNewData(SaveMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
		List<MandatoryTranslation> newMandatoryTranslations = createNewMandatoryTranslations(dto.getItemFieldConfigs(), allItemFieldConfigs);
		mandatoryTranslationService.saveAll(newMandatoryTranslations);
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
}
