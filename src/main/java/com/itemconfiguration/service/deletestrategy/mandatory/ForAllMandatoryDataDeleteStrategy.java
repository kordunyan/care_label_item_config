package com.itemconfiguration.service.deletestrategy.mandatory;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.utils.ItemFieldConfigUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("ForAllMandatoryDataDeleteStrategy")
public class ForAllMandatoryDataDeleteStrategy implements MandatoryDataDeleteStrategy{

    private final ItemFieldConfigService itemFieldConfigService;

    @Resource(name = "MandatoryFieldsDeleter")
    private MandatoryDataDeleter mandatoryFieldsDeleter;

    @Resource(name = "MandatoryTranslationsDeleter")
    private MandatoryDataDeleter mandatoryTranslationDeleter;

    public ForAllMandatoryDataDeleteStrategy(ItemFieldConfigService itemFieldConfigService) {
        this.itemFieldConfigService = itemFieldConfigService;
    }

    @Override
    public void delete(DeleteMandatoryDataDto dto) {
        Map<String, List<ItemFieldConfig>> allItemFieldConfigs = getAllItemFieldConfigs(dto);
        mandatoryFieldsDeleter.delete(dto, allItemFieldConfigs);
        mandatoryTranslationDeleter.delete(dto, allItemFieldConfigs);
    }

    private Map<String, List<ItemFieldConfig>> getAllItemFieldConfigs(DeleteMandatoryDataDto dto) {
        List<ItemFieldConfig> itemFieldConfigs = itemFieldConfigService.getByFieldConfigNamesAndItemNumbers(
                getItemFieldConfigNames(dto), dto.getItemNumbers());
        if (dto.getItemFieldsCriteria() == null || CollectionUtils.isEmpty(dto.getItemFieldsCriteria().getMultipleFields())) {
            return ItemFieldConfigUtils.createItemFieldConfigsMap(itemFieldConfigs);
        }
        return ItemFieldConfigUtils.createItemFieldConfigsMap(ItemFieldConfigUtils.filterByMultipleFields(itemFieldConfigs,
                dto.getItemFieldsCriteria().getMultipleFields()));
    }

    private List<String> getItemFieldConfigNames(DeleteMandatoryDataDto dto) {
        Set<String> result = new HashSet<>();
        if (MapUtils.isNotEmpty(dto.getFieldsToDeleteByFieldName())) {
            result.addAll(dto.getFieldsToDeleteByFieldName().keySet());
        }
        if (MapUtils.isNotEmpty(dto.getTranslationsToDeleteByFieldName())) {
            result.addAll(dto.getTranslationsToDeleteByFieldName().keySet());
        }
        return new ArrayList<>(result);
    }
}
