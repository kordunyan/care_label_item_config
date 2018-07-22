package com.kordunyan.dao;

import com.kordunyan.domain.Field;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FieldDAO extends CrudRepository<Field, Long> {

	@Query("SELECT DISTINCT f.value FROM Field f WHERE f.fieldConfigName = ?1 ORDER BY f.value")
	List<String> findAllByFieldConfigname(String fieldConfigName);

	@Modifying
	@Transactional
	@Query(value = "UPDATE field SET value = ?1 WHERE id IN (SELECT all_fields.id " +
			"FROM field f " +
			"JOIN field item_f ON item_f.field_set_id = f.field_set_id AND item_f.field_config_name = 'D2COMM_ITEM_NUMBER' " +
			"JOIN field all_items ON all_items.value = item_f.value AND all_items.field_config_name = 'D2COMM_ITEM_NUMBER' " +
			"JOIN field all_fields ON all_fields.field_set_id = all_items.field_set_id AND all_fields.field_config_name = f.field_config_name " +
			"WHERE f.id = ?2)", nativeQuery = true)
	int updateAllForItem(String value, long id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM field WHERE id IN (SELECT all_fields.id " +
			"FROM field f " +
			"JOIN field item_f ON item_f.field_set_id = f.field_set_id AND item_f.field_config_name = 'D2COMM_ITEM_NUMBER' " +
			"JOIN field all_items ON all_items.value = item_f.value AND all_items.field_config_name = 'D2COMM_ITEM_NUMBER' " +
			"JOIN field all_fields ON all_fields.field_set_id = all_items.field_set_id AND all_fields.field_config_name = f.field_config_name " +
			"WHERE f.id = ?1)", nativeQuery = true)
	int deleteForAllItems(Long id);
}
