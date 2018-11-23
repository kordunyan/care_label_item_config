package com.itemconfiguration.service.savestrategy.mandatory;

import com.itemconfiguration.dto.SaveMandatoryDataDto;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class MandatoryDataSaveStrategyProvider {

    @Resource(name = "MandatoryDataSaveForItemNumberStrategy")
    private MandatoryDataSaveStrategy itemNumbersMandatoryDataSaveStrategy;

    @Resource(name = "DefaultMandatoryDataSaveStrategy")
    private MandatoryDataSaveStrategy defaultSaveStrategy;

    public MandatoryDataSaveStrategy getSaveStrategy(SaveMandatoryDataDto dto) {
        if (dto.isSaveForAll()) {
            return itemNumbersMandatoryDataSaveStrategy;
        }
        return defaultSaveStrategy;
    }
}
