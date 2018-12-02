package com.itemconfiguration.service.savestrategy.mandatory;


import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.utils.ItemFieldConfigUtils;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.itemconfiguration.utils.ItemUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component("MandatoryDataSaveForItemNumberStrategy")
public class MandatoryDataSaveForItemNumberStrategy implements MandatoryDataSaveStrategy {

    private final ItemFieldConfigService itemFieldConfigService;

    @Resource(name = "MandatoryFieldsForItemNumbersSaver")
    private MandatoryDataSaver mandatoryFieldsForItemNumbersSaver;

    @Resource(name = "MandatoryTranslationsForItemNumbersSaver")
    private MandatoryDataSaver mandatoryTranslationsForItemNumbersSaver;

    public MandatoryDataSaveForItemNumberStrategy(ItemFieldConfigService itemFieldConfigService) {
        this.itemFieldConfigService = itemFieldConfigService;
    }

    @Override
    public void save(SaveMandatoryDataDto dto) {
        if (CollectionUtils.isEmpty(dto.getItemFieldConfigs())) {
            throw new IllegalArgumentException("[itemFieldConfigs] should not be empty");
        }
        if (CollectionUtils.isEmpty(dto.getItemNumbers())) {
            throw new IllegalArgumentException("[itemNumbers] should not be empty");
        }
        Map<String, List<ItemFieldConfig>> allItemFieldConfigs = getAllItemFieldConfigs(dto);
        mandatoryFieldsForItemNumbersSaver.saveNewData(dto, allItemFieldConfigs);
        mandatoryTranslationsForItemNumbersSaver.saveNewData(dto, allItemFieldConfigs);
    }

    private Map<String, List<ItemFieldConfig>> getAllItemFieldConfigs(SaveMandatoryDataDto dto) {
        List<ItemFieldConfig> itemFieldConfigs = itemFieldConfigService.getByFieldConfigNamesAndItemNumbers(
                getItemFieldConfigNames(dto.getItemFieldConfigs()), dto.getItemNumbers());
        return ItemFieldConfigUtils.createItemFieldConfigsMap(ItemFieldConfigUtils.filterByitemFieldsCriteria(itemFieldConfigs,
                dto.getItemFieldsCriteria()));
    }

    private List<String> getItemFieldConfigNames(List<ItemFieldConfig> itemFieldConfigs) {
        return itemFieldConfigs.stream()
                .map(itemFieldConfig -> itemFieldConfig.getFieldConfig().getName())
                .collect(Collectors.toList());
    }

}
