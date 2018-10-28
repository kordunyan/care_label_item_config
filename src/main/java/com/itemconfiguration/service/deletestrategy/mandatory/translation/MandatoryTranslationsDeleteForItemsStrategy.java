package com.itemconfiguration.service.deletestrategy.mandatory.translation;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.MandatoryTranslationService;
import com.itemconfiguration.utils.DomainUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component("mandatory-translation-delete-for_items")
public class MandatoryTranslationsDeleteForItemsStrategy implements MandatoryTranslationDeleteStrategy {

	private MandatoryTranslationService mandatoryTranslationService;
	private ItemFieldConfigService itemFieldConfigService;

	public MandatoryTranslationsDeleteForItemsStrategy(MandatoryTranslationService mandatoryTranslationService,
			ItemFieldConfigService itemFieldConfigService) {
		this.mandatoryTranslationService = mandatoryTranslationService;
		this.itemFieldConfigService = itemFieldConfigService;
	}

	@Override
	public void delete(DeleteMandatoryDataDto dto) {
		if (MapUtils.isEmpty(dto.getTranslationsToDeleteByFieldName())) {
			throw new IllegalArgumentException("[translationsToDeleteByFieldName] param should not by empty");
		}
		Map<String, List<ItemFieldConfig>> allItemFieldConfigs = itemFieldConfigService.getByFieldConfigNamesAndItemNumbersMap(
				getItemFieldConfigNames(dto.getTranslationsToDeleteByFieldName()), dto.getItemNumbers());
		List<MandatoryTranslation> mandatoryTranslations = getMandatoryTranslations(dto.getTranslationsToDeleteByFieldName(),
				allItemFieldConfigs);
		mandatoryTranslationService.deleteALl(mandatoryTranslations);
	}

	private List<MandatoryTranslation> getMandatoryTranslations(Map<String, List<MandatoryTranslation>> translationsToDelete,
			Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
		List<MandatoryTranslation> result = new ArrayList<>();
		for (String fieldConfigName : translationsToDelete.keySet()) {
			Set<String> lanfuageCodes = DomainUtils.getMandatoryTranslationLanguageCodes(translationsToDelete.get(fieldConfigName));
			List<MandatoryTranslation> toDelete = allItemFieldConfigs.get(fieldConfigName).stream()
					.flatMap(itemFieldConfig -> itemFieldConfig.getMandatoryTranslations().stream())
					.filter(mandatoryTranslation -> lanfuageCodes.contains(mandatoryTranslation.getLanguage().getCode()))
					.collect(Collectors.toList());
			result.addAll(toDelete);
		}
		return result;
	}

	private List<String> getItemFieldConfigNames(Map<String, List<MandatoryTranslation>> mandatoryTranslationsMap) {
		return new ArrayList<>(mandatoryTranslationsMap.keySet());
	}
}
