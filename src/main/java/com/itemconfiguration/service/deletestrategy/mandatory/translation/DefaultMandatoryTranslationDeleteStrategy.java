package com.itemconfiguration.service.deletestrategy.mandatory.translation;

import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.service.MandatoryTranslationService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("default-mandatory-translation-delete")
public class DefaultMandatoryTranslationDeleteStrategy implements MandatoryTranslationDeleteStrategy {

	private MandatoryTranslationService mandatoryTranslationService;

	public DefaultMandatoryTranslationDeleteStrategy(MandatoryTranslationService mandatoryTranslationService) {
		this.mandatoryTranslationService = mandatoryTranslationService;
	}

	@Override
	public void delete(DeleteMandatoryDataDto dto) {
		if (MapUtils.isEmpty(dto.getTranslationsToDeleteByFieldName())) {
			throw new IllegalArgumentException("[translationsToDeleteByFieldName] param should not by empty");
		}
		List<MandatoryTranslation> translationToDelete = getTranslationsToDelete(dto.getTranslationsToDeleteByFieldName());
		mandatoryTranslationService.deleteALl(translationToDelete);
	}

	private List<MandatoryTranslation> getTranslationsToDelete(Map<String, List<MandatoryTranslation>> translationsMap) {
		return translationsMap.keySet().stream()
				.flatMap(fieldConfigName -> translationsMap.get(fieldConfigName).stream())
				.filter(translation -> Objects.nonNull(translation.getId()))
				.collect(Collectors.toList());
	}
}
