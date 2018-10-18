package com.itemconfiguration.service.savestrategy.mandatorytranslation;

import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.SaveMandatoryDataDto;

import java.util.List;

public interface MandatoryTranslationSaveStrategy {
    List<MandatoryTranslation> save(SaveMandatoryDataDto dto);
}
