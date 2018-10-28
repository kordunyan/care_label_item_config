package com.itemconfiguration.service;

import com.itemconfiguration.AppProperties;
import com.itemconfiguration.dao.ItemFieldConfigDAO;
import com.itemconfiguration.domain.AppFields;
import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.ItemFieldConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemFieldConfigService {

	private ItemFieldConfigDAO itemFieldConfigDAO;

	public ItemFieldConfigService(ItemFieldConfigDAO itemFieldConfigDAO) {
		this.itemFieldConfigDAO = itemFieldConfigDAO;
	}

	public void save(ItemFieldConfig itemFieldConfig) {
		itemFieldConfigDAO.save(itemFieldConfig);
	}

	public void saveAll(List<ItemFieldConfig> itemFieldConfigs) {
		itemFieldConfigDAO.saveAll(itemFieldConfigs);
	}

	public void deleteItemFieldConfigs(List<ItemFieldConfig> itemFieldConfigs) {
		if (CollectionUtils.isEmpty(itemFieldConfigs)) {
			return;
		}
		itemFieldConfigDAO.deleteAll(itemFieldConfigs);
	}

	public ItemFieldConfig getById(Long id) {
		return itemFieldConfigDAO.findById(id).orElse(null);
	}

	public List<ItemFieldConfig> getInstructionsByItemId(Long itemId) {
		return itemFieldConfigDAO.getInstructionsByItem(itemId);
	}

	public List<ItemFieldConfig> getByFieldConfigNamesAndItemNumbers(List<String> fieldConfigNames, List<String> itemmNumbers) {
		return itemFieldConfigDAO.getByFieldConfigNamesAndItemNumbers(fieldConfigNames, AppFields.D2COMM_ITEM_NUMBER, itemmNumbers);
	}

	public Map<String, List<ItemFieldConfig>> getByFieldConfigNamesAndItemNumbersMap(List<String> fieldConfigNames,
			List<String> itemmNumbers) {
		List<ItemFieldConfig> foundItemFieldConfigs = getByFieldConfigNamesAndItemNumbers(fieldConfigNames, itemmNumbers);
		if (CollectionUtils.isEmpty(foundItemFieldConfigs)) {
			return Collections.emptyMap();
		}
		return createItemFieldConfigsMap(foundItemFieldConfigs);
	}

	private Map<String, List<ItemFieldConfig>> createItemFieldConfigsMap(List<ItemFieldConfig> itemFieldConfigs) {
		Map<String, List<ItemFieldConfig>> result = new HashMap<>();
		for (ItemFieldConfig itemFieldConfig : itemFieldConfigs) {
			String fieldConfigName = itemFieldConfig.getFieldConfig().getName();
			if (!result.containsKey(fieldConfigName)) {
				result.put(fieldConfigName, new ArrayList<>());
			}
			result.get(fieldConfigName).add(itemFieldConfig);
		}
		return result;
	}
}
