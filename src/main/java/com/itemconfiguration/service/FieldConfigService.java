package com.itemconfiguration.service;

import com.itemconfiguration.dao.FieldConfigRepository;
import com.itemconfiguration.dao.resultobjects.InstructionField;
import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FieldConfigService {

	private RboPropertiesService rboPropertiesService;

	private FieldConfigRepository fieldConfigRepository;

	public FieldConfigService(RboPropertiesService rboPropertiesService, FieldConfigRepository fieldConfigRepository) {
		this.rboPropertiesService = rboPropertiesService;
		this.fieldConfigRepository = fieldConfigRepository;
	}

	public Optional<FieldConfig> findByName(String name) {
		return fieldConfigRepository.findById(name);
	}

	public void delete(FieldConfig fieldConfig) {
		fieldConfigRepository.delete(fieldConfig);
	}

	public List<FieldConfig> getAll() {
		return fieldConfigRepository.findAllByOrderByName();
	}

	public void saveAll(List<FieldConfig> fieldConfigs) {
		this.fieldConfigRepository.saveAll(fieldConfigs);
	}

	public void save(FieldConfig fieldConfig) {
		this.fieldConfigRepository.save(fieldConfig);
	}

	public List<FieldConfig> getByOwner(String owner) {
		return this.fieldConfigRepository.getByOwner(owner);
	}

	public FieldConfigsWrapper getByOwnerFieldConfigWraper(String owner) {
		List<FieldConfig> fieldConfigs = getByOwner(owner);
		return new FieldConfigsWrapper(fieldConfigs, rboPropertiesService.getMultipleFields());
	}

	public Map<String, FieldConfig> getFieldConfigsMap(String owner) {
		return this.getByOwner(owner).stream().collect(Collectors.toMap(FieldConfig::getName, Function.identity()));
	}

	public List<String> getAllOwners() {
		return fieldConfigRepository.getAllOwners();
	}

	public List<String> getAllTypes() {
		return fieldConfigRepository.getAllTypes();
	}

	public List<String> getAllNames() {
		return fieldConfigRepository.getAllNames();
	}

	public List<FieldConfig> getAllByOwners(List<String> owners) {
		return fieldConfigRepository.findAllByOwnerIn(owners);
	}

	public List<FieldConfig> getAllByTypes(List<String> types) {
		return fieldConfigRepository.findAllByTypeIn(types);
	}

	public List<FieldConfig> getAllByNames(List<String> names) {
		return fieldConfigRepository.findAllByNameIn(names);
	}

	public Map<String, List<String>> getInstructionFields() {
		List<InstructionField> instructionFields = fieldConfigRepository.getInstructionsFields();
		Map<String, List<String>> result = new HashMap<>();
		for (InstructionField instructionField : instructionFields) {
			if (!result.containsKey(instructionField.getInstructionType())) {
				result.put(instructionField.getInstructionType(), new ArrayList<>());
			}
			result.get(instructionField.getInstructionType()).add(instructionField.getFieldName());
		}
		return result;
	}

	public Map<String, FieldConfig> getInstructionsFieldConfigsMap() {
		return fieldConfigRepository.getInstructionsFieldConfigs()
				.stream()
				.collect(Collectors.toMap(FieldConfig::getName, fieldConfig -> fieldConfig));
	}
}
