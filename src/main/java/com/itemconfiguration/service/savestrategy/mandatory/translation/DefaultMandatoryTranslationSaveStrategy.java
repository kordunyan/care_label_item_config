package com.itemconfiguration.service.savestrategy.mandatory.translation;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.MandatoryTranslationService;
import com.itemconfiguration.service.savestrategy.mandatory.MandatoryDataSaveStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("default-mandatory-save")
public class DefaultMandatoryTranslationSaveStrategy implements MandatoryDataSaveStrategy {

	private final MandatoryTranslationService mandatoryTranslationService;

	public DefaultMandatoryTranslationSaveStrategy(MandatoryTranslationService mandatoryTranslationService) {
		this.mandatoryTranslationService = mandatoryTranslationService;
	}

	@Override
	public void save(SaveMandatoryDataDto dto) {
		if (CollectionUtils.isEmpty(dto.getItemFieldConfigs())) {
			throw new IllegalArgumentException("[itemFieldConfigs] should not be empty");
		}
		List<MandatoryTranslation> newMandatoryTranslations = getNewMandatoryTranslations(dto.getItemFieldConfigs());
		if (CollectionUtils.isNotEmpty(newMandatoryTranslations)) {
			mandatoryTranslationService.saveAll(newMandatoryTranslations);
		}
	}

	private List<MandatoryTranslation> getNewMandatoryTranslations(List<ItemFieldConfig> itemFieldConfigs) {
		List<MandatoryTranslation> result = new ArrayList<>();
		for (ItemFieldConfig itemFieldConfig : itemFieldConfigs) {
			if (CollectionUtils.isEmpty(itemFieldConfig.getMandatoryTranslations())) {
				continue;
			}
			List<MandatoryTranslation> currentNewTranslations = itemFieldConfig.getNewMandatoryTranslations()
					.stream()
					.peek(translation -> translation.setItemFieldConfig(itemFieldConfig))
					.collect(Collectors.toList());
			result.addAll(currentNewTranslations);
		}
		return result;
	}
}
