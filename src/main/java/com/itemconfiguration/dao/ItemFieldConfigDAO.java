package com.itemconfiguration.dao;

import com.itemconfiguration.domain.ItemFieldConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemFieldConfigDAO extends CrudRepository<ItemFieldConfig, Long> {

	@Query("SELECT f FROM ItemFieldConfig f " +
			"JOIN FieldConfig fc ON fc.name = f.fieldConfigName " +
			"WHERE f.item.id = ?1 AND fc.type <> 'TEXT_FIELD' " +
			"ORDER BY f.fieldConfigName")
	List<ItemFieldConfig> getInstructionsByItem(Long itemId);
}
