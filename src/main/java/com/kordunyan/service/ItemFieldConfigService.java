package com.kordunyan.service;

import com.kordunyan.dao.ItemDAO;
import com.kordunyan.dao.ItemFieldConfigDAO;
import com.kordunyan.domain.ItemFieldConfig;
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
}
