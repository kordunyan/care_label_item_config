package com.itemconfiguration.service.savestrategy.mandatory;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import java.util.List;
import java.util.Map;

public interface MandatoryDataSaver {
    void saveNewData(SaveMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs);
}
