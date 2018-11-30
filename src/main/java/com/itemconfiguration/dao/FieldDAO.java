package com.itemconfiguration.dao;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.FieldSet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FieldDAO extends CrudRepository<Field, Long> {

	@Query("SELECT DISTINCT f.value FROM Field f WHERE f.fieldConfigName = ?1 ORDER BY f.value")
	List<String> findAllByFieldConfigname(String fieldConfigName);

	@Modifying
	@Query("DELETE FROM Field f WHERE f.fieldSet IN ?1")
	int deleteByFieldSetIn(List<FieldSet> fieldSetIds);

	@Modifying
	@Query(value = "DELETE FROM Field f WHERE f.id IN ?1")
	void deleteAllById(List<Long> ids);

	@Modifying
	@Query("DELETE FROM Field f WHERE f.fieldSet = ?1")
	int deleteByFieldSet(FieldSet fieldSet);
}
