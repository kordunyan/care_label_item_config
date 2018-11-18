package com.itemconfiguration.export.bilder.block.itemfieldconfig;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.export.bilder.block.mandatorydata.MandatoryDataBlockBuilder;
import com.itemconfiguration.export.bilder.line.ItemFieldConfigLineBuilder;
import com.itemconfiguration.export.bilder.line.StaticLines;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("ItemFieldConfigWithMandatoryDataLinesBuilder")
public class ItemFieldConfigWithMandatoryDataLinesBuilder implements ItemFieldConfigLinesBuilder {

    private ItemFieldConfigLineBuilder itemFieldConfigLineBuilder;
    private MandatoryDataBlockBuilder mandatoryDataBlockBuilder;

    public ItemFieldConfigWithMandatoryDataLinesBuilder(ItemFieldConfigLineBuilder itemFieldConfigLineBuilder,
            MandatoryDataBlockBuilder mandatoryDataBlockBuilder) {
        this.itemFieldConfigLineBuilder = itemFieldConfigLineBuilder;
        this.mandatoryDataBlockBuilder = mandatoryDataBlockBuilder;
    }

    @Override
    public List<String> buildLines(List<ItemFieldConfig> itemFieldConfigs, Item item) {
        if (CollectionUtils.isEmpty(itemFieldConfigs)) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        for (ItemFieldConfig itemFieldConfig : itemFieldConfigs) {
            result.add(itemFieldConfigLineBuilder.buildItemFieldConfigLineWithReturningId(itemFieldConfig, item));
            result.addAll(mandatoryDataBlockBuilder.build(itemFieldConfig));
        }
        return result;
    }
}
