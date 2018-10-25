package com.itemconfiguration.dao;

import com.itemconfiguration.dao.resultobjects.InstructionField;
import com.itemconfiguration.domain.FieldConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FieldConfigRepository extends CrudRepository<FieldConfig, String> {
	List<FieldConfig> getByOwner(String owner);

	List<FieldConfig> findAllByOrderByName();

	@Query(value = "SELECT i.name as instructionName, f.name as fieldName FROM FieldConfig i JOIN FieldConfig f ON f.owner = i.type")
	List<InstructionField> getInstructionsFields();

	@Query(value = "SELECT DISTINCT f FROM FieldConfig i JOIN FieldConfig f ON f.owner = i.type")
	List<FieldConfig> getInstructionsFieldConfigs();
 }
