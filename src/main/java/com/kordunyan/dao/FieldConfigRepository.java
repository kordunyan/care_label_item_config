package com.kordunyan.dao;

import com.kordunyan.domain.FieldConfig;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FieldConfigRepository extends CrudRepository<FieldConfig, String> {
	List<FieldConfig> getByOwner(String owner);
}
