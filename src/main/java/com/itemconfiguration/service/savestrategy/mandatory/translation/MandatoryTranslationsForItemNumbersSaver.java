package com.itemconfiguration.service.savestrategy.mandatory.translation;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.MandatoryTranslationService;
import com.itemconfiguration.service.savestrategy.mandatory.MandatoryDataSaver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component("MandatoryTranslationsForItemNumbersSaver")
public class MandatoryTranslationsForItemNumbersSaver implements MandatoryDataSaver {
	private final MandatoryTranslationService mandatoryTranslationService;

	public MandatoryTranslationsForItemNumbersSaver(MandatoryTranslationService mandatoryTranslationService) {
		this.mandatoryTranslationService = mandatoryTranslationService;
	}

	public void saveNewData(SaveMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
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
