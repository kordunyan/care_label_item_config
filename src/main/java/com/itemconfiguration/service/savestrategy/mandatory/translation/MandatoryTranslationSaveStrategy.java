package com.itemconfiguration.service.savestrategy.mandatory.translation;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.SaveMandatoryDataDto;

import java.util.List;

public interface MandatoryTranslationSaveStrategy {
    List<ItemFieldConfig> save(SaveMandatoryDataDto dto);
}
