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

	@Query("SELECT i FROM Item i INNER JOIN i.fields as f WHERE f.fieldConfigName = ?1 AND f.value in ?2 ORDER BY i.id")
	List<Item> findAllByFieldsLight(String fieldType, List<String> fieldValue);

	@Modifying
	@Transactional
	@Query("UPDATE Item i SET i.ipps = ?1, i.sb = ?2 WHERE i.id = ?3")
	int updateLocationEnablemend(boolean ipps, boolean sb, Long id);
}
