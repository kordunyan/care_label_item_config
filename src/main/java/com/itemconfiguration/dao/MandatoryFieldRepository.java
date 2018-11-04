package com.itemconfiguration.dao;

import com.itemconfiguration.domain.MandatoryField;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MandatoryFieldRepository extends CrudRepository<MandatoryField, Long>{
	@Modifying
	@Query("DELETE FROM MandatoryField f WHERE f.id IN ?1")
	void deleteByIds(List<Long> ids);
}
