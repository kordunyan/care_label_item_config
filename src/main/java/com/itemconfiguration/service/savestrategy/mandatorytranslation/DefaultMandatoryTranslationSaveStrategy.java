package com.itemconfiguration.service.savestrategy.mandatorytranslation;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.MandatoryTranslationService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("default-mandatory-save")
public class DefaultMandatoryTranslationSaveStrategy implements MandatoryTranslationSaveStrategy {

    private MandatoryTranslationService mandatoryTranslationService;

    public DefaultMandatoryTranslationSaveStrategy(MandatoryTranslationService mandatoryTranslationService) {
        this.mandatoryTranslationService = mandatoryTranslationService;
    }

    @Override
    public List<MandatoryTranslation> save(SaveMandatoryDataDto dto) {
        if (CollectionUtils.isEmpty(dto.getNewMandatoryTrnaslations())) {
            throw new IllegalArgumentException("[newMandatoryTrnaslations] should not be empty");
        }
        if (dto.getItemFieldConfig() == null) {
            throw new IllegalArgumentException("[itemFieldConfig] should not be empty");
        }
        return dto.getNewMandatoryTrnaslations();
        //return mandatoryTranslationService.saveAll(setItemFieldConfigForNewTranslations(dto.getItemFieldConfig(), dto.getNewMandatoryTrnaslations()));
    }

    private List<MandatoryTranslation> setItemFieldConfigForNewTranslations(ItemFieldConfig itemFieldConfig,  List<MandatoryTranslation> mandatoryTranslations) {
        return mandatoryTranslations.stream()
                .peek(mandatoryTranslation -> mandatoryTranslation.setItemFieldConfig(itemFieldConfig))
                .collect(Collectors.toList());
    }
}
