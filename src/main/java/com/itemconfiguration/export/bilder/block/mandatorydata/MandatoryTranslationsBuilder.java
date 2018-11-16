package com.itemconfiguration.export.bilder.block.mandatorydata;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.export.bilder.line.MandatoryDataLineBuilder;
import org.springframework.stereotype.Component;

@Component("MandatoryTranslationsBuilder")
public class MandatoryTranslationsBuilder implements MandatoryDataBuilder {

    private MandatoryDataLineBuilder mandatoryDataLineBuilder;

    public MandatoryTranslationsBuilder(MandatoryDataLineBuilder mandatoryDataLineBuilder) {
        this.mandatoryDataLineBuilder = mandatoryDataLineBuilder;
    }

    @Override
    public String build(ItemFieldConfig itemFieldConfig) {
        return null;
    }
}
