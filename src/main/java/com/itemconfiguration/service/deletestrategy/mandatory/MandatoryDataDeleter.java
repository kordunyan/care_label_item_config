package com.itemconfiguration.service.deletestrategy.mandatory;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;

import java.util.List;
import java.util.Map;

public interface MandatoryDataDeleter {
    void delete(DeleteMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs);
}
