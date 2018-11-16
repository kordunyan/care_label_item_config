package com.itemconfiguration.export.bilder.block.mandatorydata;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.export.bilder.line.MandatoryDataLineBuilder;
import org.springframework.stereotype.Component;

@Component("MandatoryFieldsBuilder")
public class MandatoryFieldsBuilder implements MandatoryDataBuilder {

    private MandatoryDataLineBuilder mandatoryDataLineBuilder;

    public MandatoryFieldsBuilder(MandatoryDataLineBuilder mandatoryDataLineBuilder) {
        this.mandatoryDataLineBuilder = mandatoryDataLineBuilder;
    }

    @Override
    public String build(ItemFieldConfig itemFieldConfig) {
        return null;
    }
}
