package com.itemconfiguration.service;

import com.itemconfiguration.dao.ItemFieldConfigDAO;
import com.itemconfiguration.domain.ItemFieldConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
		for (ItemFieldConfig itemFieldConfig : itemFieldConfigs) {
			itemFieldConfigDAO.delete(itemFieldConfig);
		}
//		itemFieldConfigDAO.deleteAll(itemFieldConfigs);
	}
}
