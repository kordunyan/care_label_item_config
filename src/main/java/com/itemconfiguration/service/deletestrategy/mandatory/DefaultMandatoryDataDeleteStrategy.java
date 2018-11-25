package com.itemconfiguration.service.deletestrategy.mandatory;

import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.service.MandatoryFieldService;
import com.itemconfiguration.service.MandatoryTranslationService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("DefaultMandatoryDataDeleteStrategy")
public class DefaultMandatoryDataDeleteStrategy implements MandatoryDataDeleteStrategy {

    private final MandatoryFieldService mandatoryFieldService;
    private final MandatoryTranslationService mandatoryTranslationService;

    public DefaultMandatoryDataDeleteStrategy(MandatoryFieldService mandatoryFieldService,
            MandatoryTranslationService mandatoryTranslationService) {
        this.mandatoryFieldService = mandatoryFieldService;
        this.mandatoryTranslationService = mandatoryTranslationService;
    }

    @Override
    public void delete(DeleteMandatoryDataDto dto) {
        if (MapUtils.isNotEmpty(dto.getFieldsToDeleteByFieldName())) {
            deleteMandatoryFields(dto.getFieldsToDeleteByFieldName());
        }
        if (MapUtils.isNotEmpty(dto.getTranslationsToDeleteByFieldName())) {
            deleteTranslations(dto.getTranslationsToDeleteByFieldName());
        }
    }

    private void deleteTranslations(Map<String, List<MandatoryTranslation>> translationsMap) {
        mandatoryTranslationService.deleteALl(getTranslationsToDelete(translationsMap));
    }

    private void deleteMandatoryFields(Map<String, List<MandatoryField>> fieldsToDeleteByFieldName) {
        mandatoryFieldService.deleteALl(getFieldsToDelete(fieldsToDeleteByFieldName));
    }

    private List<MandatoryTranslation> getTranslationsToDelete(Map<String, List<MandatoryTranslation>> translationsMap) {
        return translationsMap.keySet().stream()
                .flatMap(fieldConfigName -> translationsMap.get(fieldConfigName).stream())
                .filter(translation -> Objects.nonNull(translation.getId()))
                .collect(Collectors.toList());
    }

    private List<MandatoryField> getFieldsToDelete(Map<String, List<MandatoryField>> fieldsToDeleteByFieldName) {
        return fieldsToDeleteByFieldName.keySet()
                .stream()
                .flatMap(fieldName -> fieldsToDeleteByFieldName.get(fieldName).stream())
                .filter(mandatoryField -> Objects.nonNull(mandatoryField.getId()))
                .collect(Collectors.toList());
    }
}
