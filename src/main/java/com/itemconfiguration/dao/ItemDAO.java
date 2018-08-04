package com.itemconfiguration.dao;

import com.itemconfiguration.domain.Item;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemDAO extends CrudRepository<Item, Long> {

	@Query("SELECT i FROM Item i INNER JOIN i.fields as f WHERE f.fieldConfigName = ?1 AND f.value = ?2")
	List<Item> findAllByField(String fieldType, String fieldValue);

	@Modifying
	@Transactional
	@Query("UPDATE Item i SET i.ipps = ?1, i.sb = ?2 WHERE i.id = ?3")
	int updateLocationEnablemend(boolean ipps, boolean sb, Long id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE item SET is_ipps = ?1, is_sb = ?2 WHERE id IN( " +
			"SELECT same_items.id FROM item i " +
			"JOIN field i_number ON i_number.field_set_id = i.field_set_id AND i_number.field_config_name = 'D2COMM_ITEM_NUMBER' " +
			"JOIN field items_number ON items_number.value = i_number.value AND items_number.field_config_name = 'D2COMM_ITEM_NUMBER' " +
			"JOIN item same_items ON same_items.field_set_id = items_number.field_set_id " +
			"WHERE i.id = ?3)", nativeQuery = true)
	int updateLocationEnablemendForAll(boolean ipps, boolean sb, Long id);
}
