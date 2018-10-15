package com.itemconfiguration.dao;

import com.itemconfiguration.domain.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LanguageRepository extends CrudRepository<Language, String> {
	@Query("FROM Language l ORDER BY l.name")
	List<Language> findAllOrderByName();
}
