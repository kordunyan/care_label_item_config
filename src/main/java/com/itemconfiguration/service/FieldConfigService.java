package com.itemconfiguration.service;

import com.itemconfiguration.dao.FieldConfigRepository;
import com.itemconfiguration.dao.resultobjects.InstructionField;
import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;
import org.springframework.stereotype.Service;

import java.util.*;
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

	public void saveFieldConfig(FieldConfig fieldConfig) {
		fieldConfigRepository.save(fieldConfig);
	}

	public Optional<FieldConfig> findByName(String name) {
		return fieldConfigRepository.findById(name);
	}

	public void delete(FieldConfig fieldConfig) {
		fieldConfigRepository.delete(fieldConfig);
	}

	public List<FieldConfig> getAll() {
		return (List<FieldConfig>) fieldConfigRepository.findAll();
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

	public Map<String, List<String>> getInstructionFields() {
		List<InstructionField> instructionFields = fieldConfigRepository.getInstructionsFields();
		Map<String, List<String>> result = new HashMap<>();
		for (InstructionField instructionField : instructionFields) {
			if (!result.containsKey(instructionField.getInstructionName())) {
				result.put(instructionField.getInstructionName(), new ArrayList<>());
			}
			result.get(instructionField.getInstructionName()).add(instructionField.getFieldName());
		}
		return result;
	}

	public Map<String, FieldConfig> getInstructionsFieldConfigsMap() {
		return fieldConfigRepository.getInstructionsFieldConfigs()
				.stream()
				.collect(Collectors.toMap(FieldConfig::getName, fieldConfig -> fieldConfig));
	}
}
