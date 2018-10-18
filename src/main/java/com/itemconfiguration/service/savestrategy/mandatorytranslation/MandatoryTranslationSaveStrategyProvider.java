package com.itemconfiguration.service.savestrategy.mandatorytranslation;

import com.itemconfiguration.dto.SaveMandatoryDataDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MandatoryTranslationSaveStrategyProvider {

    @Resource(name = "default-mandatory-save")
    MandatoryTranslationSaveStrategy defaultSaveStrategy;

    public MandatoryTranslationSaveStrategy getSaveStrategy(SaveMandatoryDataDto dto) {
        if (dto.isSaveForAll()) {

        }
        return defaultSaveStrategy;
    }

}
