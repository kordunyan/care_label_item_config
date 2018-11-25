package com.itemconfiguration.service.deletestrategy.mandatory.translation;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.Language;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.service.MandatoryTranslationService;
import com.itemconfiguration.service.deletestrategy.mandatory.MandatoryDataDeleter;
import com.itemconfiguration.utils.DomainUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component("MandatoryTranslationsDeleter")
public class MandatoryTranslationsDeleter implements MandatoryDataDeleter {

	private final MandatoryTranslationService mandatoryTranslationService;

	public MandatoryTranslationsDeleter(MandatoryTranslationService mandatoryTranslationService) {
		this.mandatoryTranslationService = mandatoryTranslationService;
	}

	@Override
	public void delete(DeleteMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
		if(MapUtils.isEmpty(dto.getTranslationsToDeleteByFieldName())) {
			return;
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
}
