package com.itemconfiguration.service;

import com.itemconfiguration.dao.ItemFieldConfigDAO;
import com.itemconfiguration.domain.AppFields;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.utils.ItemFieldConfigUtils;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

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

	public List<ItemFieldConfig> getAllByIds(List<Long> ids) {
		return (List<ItemFieldConfig>) this.itemFieldConfigDAO.findAllById(ids);
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
			List<String> itemNumbers) {
		List<ItemFieldConfig> foundItemFieldConfigs = getByFieldConfigNamesAndItemNumbers(fieldConfigNames, itemNumbers);
		return ItemFieldConfigUtils.createItemFieldConfigsMap(foundItemFieldConfigs);
	}
}
