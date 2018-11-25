package com.itemconfiguration.service.deletestrategy.mandatory.field;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.service.MandatoryFieldService;
import com.itemconfiguration.service.deletestrategy.mandatory.MandatoryDataDeleter;
import com.itemconfiguration.utils.DomainUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component("MandatoryFieldsDeleter")
public class MandatoryFieldsDeleter implements MandatoryDataDeleter {

    private final MandatoryFieldService mandatoryFieldService;

    public MandatoryFieldsDeleter(MandatoryFieldService mandatoryFieldService) {
        this.mandatoryFieldService = mandatoryFieldService;
    }

    @Override
    public void delete(DeleteMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
        if (MapUtils.isEmpty(dto.getFieldsToDeleteByFieldName())) {
            return;
        }
        List<MandatoryField> fieldsToDelete = getMandatoryFieldsToDelete(dto.getFieldsToDeleteByFieldName(), allItemFieldConfigs);
        mandatoryFieldService.deleteALl(fieldsToDelete);
    }

    private List<MandatoryField> getMandatoryFieldsToDelete(Map<String, List<MandatoryField>> fieldsToDeleteByFieldName,
            Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
        List<MandatoryField> result = new ArrayList<>();
        for (String fieldConfigName : fieldsToDeleteByFieldName.keySet()) {
            Set<FieldConfig> fieldsToDelete = DomainUtils.getMandatoryFieldConfigs(fieldsToDeleteByFieldName.get(fieldConfigName));
            List<MandatoryField> toDelete = allItemFieldConfigs.get(fieldConfigName)
                    .stream()
                    .flatMap(itemFieldConfig -> itemFieldConfig.getMandatoryFields().stream())
                    .filter(mandatoryField -> fieldsToDelete.contains(mandatoryField.getFieldConfig()))
                    .collect(Collectors.toList());
            result.addAll(toDelete);
        }
        return result;
    }
}
