package com.itemconfiguration.service.savestrategy.mandatory.field;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.MandatoryFieldService;
import com.itemconfiguration.service.savestrategy.mandatory.MandatoryDataSaveStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("default-mandatory-field-save")
public class DefaultMandatoryFieldSaveStrategy implements MandatoryDataSaveStrategy {

    private MandatoryFieldService mandatoryFieldService;

    public DefaultMandatoryFieldSaveStrategy(MandatoryFieldService mandatoryFieldService) {
        this.mandatoryFieldService = mandatoryFieldService;
    }

    @Override
    public List<ItemFieldConfig> save(SaveMandatoryDataDto dto) {
        if (CollectionUtils.isEmpty(dto.getItemFieldConfigs())) {
            throw new IllegalArgumentException("[itemFieldConfigs] should not be empty");
        }
        List<MandatoryField> newMandatoryFields = getNewMandatoryFields(dto.getItemFieldConfigs());
        if (CollectionUtils.isNotEmpty(newMandatoryFields)) {
            mandatoryFieldService.saveAll(newMandatoryFields);
        }
        return dto.getItemFieldConfigs();
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
