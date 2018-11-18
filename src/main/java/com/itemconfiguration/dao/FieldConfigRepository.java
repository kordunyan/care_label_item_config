package com.itemconfiguration.dao;

import com.itemconfiguration.dao.resultobjects.InstructionField;
import com.itemconfiguration.domain.FieldConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FieldConfigRepository extends CrudRepository<FieldConfig, String> {
	List<FieldConfig> getByOwner(String owner);

	List<FieldConfig> findAllByOrderByName();

	@Query(value = "SELECT DISTINCT i.type AS instructionType, f.name AS fieldName FROM FieldConfig i JOIN FieldConfig f ON f.owner = i.type")
	List<InstructionField> getInstructionsFields();

	@Query(value = "SELECT DISTINCT f FROM FieldConfig i JOIN FieldConfig f ON f.owner = i.type")
	List<FieldConfig> getInstructionsFieldConfigs();

	@Query(value = "SELECT DISTINCT f.owner FROM FieldConfig f ORDER BY f.owner")
	List<String> getAllOwners();

	@Query(value = "SELECT DISTINCT f.type FROM FieldConfig f ORDER BY f.type")
	List<String> getAllTypes();

	@Query(value = "SELECT DISTINCT f.name FROM FieldConfig f ORDER BY f.name")
	List<String> getAllNames();

	List<FieldConfig> findAllByOwnerIn(List<String> owners);

	List<FieldConfig> findAllByTypeIn(List<String> types);

	List<FieldConfig> findAllByNameIn(List<String> names);
 }
