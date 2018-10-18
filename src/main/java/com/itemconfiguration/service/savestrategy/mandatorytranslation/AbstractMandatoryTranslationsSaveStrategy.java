package com.itemconfiguration.service.savestrategy.mandatorytranslation;

import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.MandatoryTranslationService;

import java.util.List;

public class AbstractMandatoryTranslationsSaveStrategy implements MandatoryTranslationSaveStrategy {
    private MandatoryTranslationService mandatoryTranslationService;

    public AbstractMandatoryTranslationsSaveStrategy(MandatoryTranslationService mandatoryTranslationService) {
        this.mandatoryTranslationService = mandatoryTranslationService;
    }

    @Override
    public List<MandatoryTranslation> save(SaveMandatoryDataDto dto) {





        return null;
    }
}
