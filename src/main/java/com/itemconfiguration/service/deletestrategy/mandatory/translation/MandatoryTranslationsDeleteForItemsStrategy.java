package com.itemconfiguration.service.deletestrategy.mandatory.translation;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.Language;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.MandatoryTranslationService;
import com.itemconfiguration.service.deletestrategy.mandatory.AbstractMandatoryDataDeleteForItemNumbersStrategy;
import com.itemconfiguration.utils.DomainUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component("mandatory-translation-delete-for_items")
public class MandatoryTranslationsDeleteForItemsStrategy extends AbstractMandatoryDataDeleteForItemNumbersStrategy {

	private final MandatoryTranslationService mandatoryTranslationService;

	public MandatoryTranslationsDeleteForItemsStrategy(MandatoryTranslationService mandatoryTranslationService,
			ItemFieldConfigService itemFieldConfigService) {
		super(itemFieldConfigService);
		this.mandatoryTranslationService = mandatoryTranslationService;
	}

	@Override
	protected void deleteMandatoryData(DeleteMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
		if (MapUtils.isEmpty(dto.getTranslationsToDeleteByFieldName())) {
			throw new IllegalArgumentException("[translationsToDeleteByFieldName] param should not by empty");
		}
		List<MandatoryTranslation> mandatoryTranslations = getMandatoryTranslations(dto.getTranslationsToDeleteByFieldName(),
				allItemFieldConfigs);
		mandatoryTranslationService.deleteALl(mandatoryTranslations);
	}

	private List<MandatoryTranslation> getMandatoryTranslations(Map<String, List<MandatoryTranslation>> translationsToDelete,
			Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
		List<MandatoryTranslation> result = new ArrayList<>();
		for (String fieldConfigName : translationsToDelete.keySet()) {
			Set<Language> lanfuageCodes = DomainUtils.getMandatoryTranslationLanguages(translationsToDelete.get(fieldConfigName));
			List<MandatoryTranslation> toDelete = allItemFieldConfigs.get(fieldConfigName).stream()
					.flatMap(itemFieldConfig -> itemFieldConfig.getMandatoryTranslations().stream())
					.filter(mandatoryTranslation -> lanfuageCodes.contains(mandatoryTranslation.getLanguage()))
					.collect(Collectors.toList());
			result.addAll(toDelete);
		}
		return result;
	}

	@Override
	protected List<String> getItemFieldConfigNames(DeleteMandatoryDataDto dto) {
		return new ArrayList<>(dto.getTranslationsToDeleteByFieldName().keySet());
	}
}
