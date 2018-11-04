package com.itemconfiguration.service.savestrategy.mandatory;

import com.itemconfiguration.dto.SaveMandatoryDataDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MandatoryDataSaveStrategyProvider {

    @Resource(name = "default-mandatory-save")
    private MandatoryDataSaveStrategy defaultTranslationSaveStrategy;

    @Resource(name = "item-numbers-mandatory-save")
    private MandatoryDataSaveStrategy itemNumbersTranslationSaveStrategy;

    @Resource(name = "default-mandatory-field-save")
    private MandatoryDataSaveStrategy defaultFieldSaveStrategy;

    @Resource(name = "item-numbers-mandatory-fields-save")
    private MandatoryDataSaveStrategy itemNumbersFieldsSaveStrategy;

    public MandatoryDataSaveStrategy getTranslationSaveStrategy(SaveMandatoryDataDto dto) {
        if (dto.isSaveForAll()) {
            return itemNumbersTranslationSaveStrategy;
        }
        return defaultTranslationSaveStrategy;
    }

    public MandatoryDataSaveStrategy getFieldsSaveStrategy(SaveMandatoryDataDto dto) {
        if (dto.isSaveForAll()) {
            return itemNumbersFieldsSaveStrategy;
        }
        return defaultFieldSaveStrategy;
    }
}
