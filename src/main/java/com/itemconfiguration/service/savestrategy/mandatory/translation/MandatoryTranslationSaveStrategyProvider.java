package com.itemconfiguration.service.savestrategy.mandatory.translation;

import com.itemconfiguration.dto.SaveMandatoryDataDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MandatoryTranslationSaveStrategyProvider {

    @Resource(name = "default-mandatory-save")
    MandatoryTranslationSaveStrategy defaultSaveStrategy;

    @Resource(name = "item-numbers-mandatory-save")
    MandatoryTranslationSaveStrategy itemNumbersSaveStrategy;

    public MandatoryTranslationSaveStrategy getSaveStrategy(SaveMandatoryDataDto dto) {
        if (dto.isSaveForAll()) {
            return itemNumbersSaveStrategy;
        }
        return defaultSaveStrategy;
    }

}
