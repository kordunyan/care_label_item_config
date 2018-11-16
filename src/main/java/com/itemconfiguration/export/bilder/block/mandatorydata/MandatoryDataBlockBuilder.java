package com.itemconfiguration.export.bilder.block.mandatorydata;

import com.itemconfiguration.domain.ItemFieldConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class MandatoryDataBlockBuilder {

    @Resource(name = "MandatoryFieldsBuilder")
    private MandatoryDataBuilder mandatoryFieldsBuilder;

    @Resource(name = "MandatoryTranslationsBuilder")
    private MandatoryDataBuilder mandatoryTranslationsBuilder;


    public List<String> build(ItemFieldConfig itemFieldConfig) {
        List<String> result = new ArrayList<>();
        result.add(mandatoryFieldsBuilder.build(itemFieldConfig));
        result.add(mandatoryTranslationsBuilder.build(itemFieldConfig));
        return result;
    }
}
