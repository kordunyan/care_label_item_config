package com.itemconfiguration.export.bilder.block.itemfieldconfig;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.export.bilder.line.ItemFieldConfigLineBuilder;
import com.itemconfiguration.export.bilder.line.StaticLines;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemFieldConfigBlockBuilder {

	private ItemFieldConfigLineBuilder itemFieldConfigLineBuilder;

	public ItemFieldConfigBlockBuilder(ItemFieldConfigLineBuilder itemFieldConfigLineBuilder) {
		this.itemFieldConfigLineBuilder = itemFieldConfigLineBuilder;
	}

	public List<String> build(ItemWithFieldsMap itemWraper) {
		Item item = itemWraper.getItem();
		if (CollectionUtils.isEmpty(item.getItemFieldConfigs())) {
			return Collections.emptyList();
		}
		List<String> result = new ArrayList<>();
		result.add(buildInsertInto());
		result.add(buildItemFieldConfigsLines(item.getItemFieldConfigs(), item));
		return result;
	}

	private String buildItemFieldConfigsLines(List<ItemFieldConfig> itemFieldConfigs, Item item) {
		return itemFieldConfigs.stream()
				.map(itemFieldConfig -> itemFieldConfigLineBuilder.buildItemFieldConfigLine(itemFieldConfig, item))
				.collect(Collectors.joining(StaticLines.INSERT_VALUES_SEPARATOR)) + ";";
	}

	private String buildInsertInto() {
		return new StringBuilder()
				.append("INSERT INTO item_field_config").append(StaticLines.NEW_LINE)
				.append(itemFieldConfigLineBuilder.buildFieldNames())
				.append(" VALUES").append(StaticLines.NEW_LINE)
				.toString();
	}
}
