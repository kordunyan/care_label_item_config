package com.itemconfiguration.service.savestrategy.mandatory;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.MandatoryFieldService;
import com.itemconfiguration.service.MandatoryTranslationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component("DefaultMandatoryDataSaveStrategy")
public class DefaultMandatoryDataSaveStrategy implements MandatoryDataSaveStrategy {

    private final MandatoryTranslationService mandatoryTranslationService;
    private final MandatoryFieldService mandatoryFieldService;

    public DefaultMandatoryDataSaveStrategy(MandatoryTranslationService mandatoryTranslationService,
            MandatoryFieldService mandatoryFieldService) {
        this.mandatoryTranslationService = mandatoryTranslationService;
        this.mandatoryFieldService = mandatoryFieldService;
    }

    @Override
    public void save(SaveMandatoryDataDto dto) {
        if (CollectionUtils.isEmpty(dto.getItemFieldConfigs())) {
            throw new IllegalArgumentException("[itemFieldConfigs] should not be empty");
        }
        saveMandatoryFields(dto);
        saveMandatoryTranslations(dto);
    }


    private void saveMandatoryFields(SaveMandatoryDataDto dto) {
        List<MandatoryField> newMandatoryFields = getNewMandatoryFields(dto.getItemFieldConfigs());
        if (CollectionUtils.isNotEmpty(newMandatoryFields)) {
            mandatoryFieldService.saveAll(newMandatoryFields);
        }
    }

    private void saveMandatoryTranslations(SaveMandatoryDataDto dto) {
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

    private List<MandatoryField> getNewMandatoryFields(List<ItemFieldConfig> itemFieldConfigs) {
        List<MandatoryField> result = new ArrayList<>();
        for (ItemFieldConfig itemFieldConfig : itemFieldConfigs) {
            List<MandatoryField> newFields = itemFieldConfig.getMandatoryFields()
                    .stream()
                    .filter(field -> Objects.isNull(field.getId()))
                    .peek(field -> field.setItemFieldConfig(itemFieldConfig))
                    .collect(Collectors.toList());
            result.addAll(newFields);
        }
        return result;
    }
}
