package com.itemconfiguration.export;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.dto.ExportDataDto;
import com.itemconfiguration.export.bilder.BaseScreeptsBuilder;
import com.itemconfiguration.export.bilder.block.item.ItemBlockBuilder;
import com.itemconfiguration.export.bilder.block.itemfieldconfig.ItemFieldConfigBlockBuilder;
import com.itemconfiguration.export.bilder.block.itemfields.ItemFieldsBlockBuilder;
import com.itemconfiguration.export.bilder.line.StaticLines;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllItemsConfigurationExportBuilder implements ItemConfigurationExportBuilder {

    private ItemFieldsBlockBuilder itemFieldsBlockBuilder;
    private ItemBlockBuilder itemBlockBuilder;
    private ItemFieldConfigBlockBuilder itemFieldConfigBlockBuilder;
    private BaseScreeptsBuilder baseScreeptsBuilder;

    public AllItemsConfigurationExportBuilder(ItemFieldsBlockBuilder itemFieldsBlockBuilder,
            ItemBlockBuilder itemBlockBuilder, ItemFieldConfigBlockBuilder itemFieldConfigBlockBuilder,
            BaseScreeptsBuilder baseScreeptsBuilder) {
        this.itemFieldsBlockBuilder = itemFieldsBlockBuilder;
        this.itemBlockBuilder = itemBlockBuilder;
        this.itemFieldConfigBlockBuilder = itemFieldConfigBlockBuilder;
        this.baseScreeptsBuilder = baseScreeptsBuilder;
    }

    @Override
    public String build(ExportDataDto exportData) {
        List<Item> items = exportData.getItems();
        FieldConfigsWrapper fieldConfigsWrapper = exportData.getFieldConfigsWrapper();
        if (CollectionUtils.isEmpty(items)) {
            return StringUtils.EMPTY;
        }
        List<String> result = new ArrayList<>();
        result.add(baseScreeptsBuilder.buildDeclarations());
        result.addAll(baseScreeptsBuilder.buildDeleteItemsData(exportData.getItemNumbers()));
        items.stream()
                .map(ItemWithFieldsMap::new)
                .forEach(itemWithFieldsMap -> {
                    result.addAll(itemFieldsBlockBuilder.build(itemWithFieldsMap, fieldConfigsWrapper));
                    result.add(StaticLines.getBlockSeparator());
                    result.addAll(itemBlockBuilder.build(itemWithFieldsMap));
                    result.add(StaticLines.getBlockSeparator());
                    result.addAll(itemFieldConfigBlockBuilder.build(itemWithFieldsMap));
                    result.add(StaticLines.getBlockSeparator());
                });
        result.add(baseScreeptsBuilder.buildFinalScrepts());
        return StringUtils.join(result, "");
    }
}
