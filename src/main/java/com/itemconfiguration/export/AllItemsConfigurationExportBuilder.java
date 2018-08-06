package com.itemconfiguration.export;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.dto.ExportDataDto;
import com.itemconfiguration.export.bilder.block.itemfields.ItemFieldsBlockBuilder;
import com.itemconfiguration.export.bilder.line.StaticLineBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllItemsConfigurationExportBuilder implements ItemConfigurationExportBuilder {

    private ItemFieldsBlockBuilder itemFieldsBlockBuilder;

    public AllItemsConfigurationExportBuilder(ItemFieldsBlockBuilder itemFieldsBlockBuilder) {
        this.itemFieldsBlockBuilder = itemFieldsBlockBuilder;
    }

    @Override
    public String build(ExportDataDto exportData) {
        List<Item> items = exportData.getItems();
        FieldConfigsWrapper fieldConfigsWrapper = exportData.getFieldConfigsWrapper();
        if (CollectionUtils.isEmpty(items)) {
            return StringUtils.EMPTY;
        }
        List<String> result = new ArrayList<>();

        items.stream()
                .map(ItemWithFieldsMap::new)
                .forEach(itemWithFieldsMap -> {
                    result.addAll(itemFieldsBlockBuilder.build(itemWithFieldsMap, fieldConfigsWrapper));
                    result.add(StaticLineBuilder.getBlockSeparator());
                });
        return StringUtils.join(result, "");
    }
}
