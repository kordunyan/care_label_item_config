package com.itemconfiguration.utils;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.Language;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.domain.MandatoryTranslation;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DomainUtils {

	public static Set<Language> getMandatoryTranslationLanguages(List<MandatoryTranslation> mandatoryTranslations) {
		if (CollectionUtils.isEmpty(mandatoryTranslations)) {
			return Collections.emptySet();
		}
		return mandatoryTranslations.stream()
				.map(MandatoryTranslation::getLanguage)
				.collect(Collectors.toSet());
	}

	public static Set<FieldConfig> getMandatoryFieldConfigs(List<MandatoryField> mandatoryFields) {
		if (CollectionUtils.isEmpty(mandatoryFields)) {
			return Collections.emptySet();
		}
		return mandatoryFields.stream()
				.map(MandatoryField::getFieldConfig)
				.collect(Collectors.toSet());
	}
}
