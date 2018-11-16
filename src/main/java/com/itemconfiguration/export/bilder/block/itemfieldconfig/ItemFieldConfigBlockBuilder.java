package com.itemconfiguration.export.bilder.block.itemfieldconfig;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ItemFieldConfigBlockBuilder {

	@Resource(name = "ItemFieldConfigWithMandatoryDataLinesBuilder")
	private ItemFieldConfigLinesBuilder itemFieldConfigWithMandatoryDataLinesBuilder;

	@Resource(name = "ItemFieldConfigWithoutMandatoryDataLinesBuilder")
	private ItemFieldConfigLinesBuilder itemFieldConfigWithoutMandatoryDataLinesBuilder;


	public List<String> build(ItemWithFieldsMap itemWraper) {
		Item item = itemWraper.getItem();
		if (CollectionUtils.isEmpty(item.getItemFieldConfigs())) {
			return Collections.emptyList();
		}
		List<String> result = new ArrayList<>();
		Map<Boolean, List<ItemFieldConfig>> fieldByContainsMandatoryData = groupByContainsMandatoryData(item.getItemFieldConfigs());
		result.addAll(itemFieldConfigWithMandatoryDataLinesBuilder.buildLines(fieldByContainsMandatoryData.get(true), item));
		result.addAll(itemFieldConfigWithoutMandatoryDataLinesBuilder.buildLines(fieldByContainsMandatoryData.get(false), item));
		return result;
	}

	protected Map<Boolean, List<ItemFieldConfig>> groupByContainsMandatoryData(List<ItemFieldConfig> itemFieldConfigs) {
		if (CollectionUtils.isEmpty(itemFieldConfigs)) {
			return Collections.emptyMap();
		}
		return itemFieldConfigs.stream()
				.collect(Collectors.partitioningBy(itemFieldConfig -> CollectionUtils.isNotEmpty(itemFieldConfig.getMandatoryFields())
						|| CollectionUtils.isNotEmpty(itemFieldConfig.getMandatoryTranslations())));
	}



}
