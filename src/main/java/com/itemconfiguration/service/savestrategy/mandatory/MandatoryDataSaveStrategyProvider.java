package com.itemconfiguration.service.savestrategy.mandatory;

import com.itemconfiguration.dto.SaveMandatoryDataDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MandatoryDataSaveStrategyProvider {

    @Resource(name = "default-mandatory-save")
    MandatoryDataSaveStrategy defaultTranslationSaveStrategy;

    @Resource(name = "item-numbers-mandatory-save")
    MandatoryDataSaveStrategy itemNumbersTranslationSaveStrategy;

    @Resource(name = "default-mandatory-field-save")
    MandatoryDataSaveStrategy defaultFieldSaveStrategy;

    public MandatoryDataSaveStrategy getTranslationSaveStrategy(SaveMandatoryDataDto dto) {
        if (dto.isSaveForAll()) {
            return itemNumbersTranslationSaveStrategy;
        }
        return defaultTranslationSaveStrategy;
    }

    public MandatoryDataSaveStrategy getFieldsSaveStrategy(SaveMandatoryDataDto dto) {
        return defaultFieldSaveStrategy;
    }
}
