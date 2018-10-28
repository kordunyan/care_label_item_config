package com.itemconfiguration.utils;

import com.itemconfiguration.domain.MandatoryTranslation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DomainUtils {

	public static Set<String> getMandatoryTranslationLanguageCodes(List<MandatoryTranslation> mandatoryTranslations) {
		return mandatoryTranslations.stream()
				.map(mandatoryTranslation -> mandatoryTranslation.getLanguage().getCode())
				.collect(Collectors.toSet());
	}
}
