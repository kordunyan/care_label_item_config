package com.itemconfiguration.dao;

import com.itemconfiguration.domain.FieldConfig;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FieldConfigRepository extends CrudRepository<FieldConfig, String> {
	List<FieldConfig> getByOwner(String owner);
}
