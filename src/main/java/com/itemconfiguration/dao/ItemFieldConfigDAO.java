package com.itemconfiguration.dao;

import com.itemconfiguration.domain.ItemFieldConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemFieldConfigDAO extends CrudRepository<ItemFieldConfig, Long> {

	@Query("SELECT f FROM ItemFieldConfig f " +
			"JOIN f.fieldConfig fc " +
			"WHERE f.item.id = ?1 AND fc.type <> 'TEXT_FIELD' " +
			"ORDER BY fc.name")
	List<ItemFieldConfig> getInstructionsByItem(Long itemId);

	@Query(value = "SELECT ifc FROM ItemFieldConfig ifc " +
			"JOIN ifc.fieldConfig fc " +
			"JOIN ifc.item i " +
			"JOIN i.fields f " +
			"WHERE fc.name IN ?1 AND f.fieldConfigName = ?2 AND f.value IN ?3")
	List<ItemFieldConfig> getByFieldConfigNamesAndItemNumbers(List<String> fieldConfigNames, String itemFieldName,
			List<String> itemNumbers);
}
