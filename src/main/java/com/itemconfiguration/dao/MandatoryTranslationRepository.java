package com.itemconfiguration.dao;

import com.itemconfiguration.domain.MandatoryTranslation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MandatoryTranslationRepository extends CrudRepository<MandatoryTranslation, Long> {

	@Modifying
	@Query("DELETE FROM MandatoryTranslation t WHERE t.id IN ?1")
	void deleteByIds(List<Long> ids);

}
