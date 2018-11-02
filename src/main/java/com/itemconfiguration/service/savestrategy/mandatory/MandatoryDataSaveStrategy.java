package com.itemconfiguration.service.savestrategy.mandatory;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.SaveMandatoryDataDto;

import java.util.List;

public interface MandatoryDataSaveStrategy {
    List<ItemFieldConfig> save(SaveMandatoryDataDto dto);
}
