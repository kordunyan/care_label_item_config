package com.itemconfiguration.service;

import com.itemconfiguration.dao.FieldConfigRepository;
import com.itemconfiguration.domain.FieldConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FieldConfigService {

	private FieldConfigRepository fieldConfigRepository;

	public FieldConfigService(FieldConfigRepository fieldConfigRepository) {
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

	public List<FieldConfig> getByOwner(String owner) {
		return this.fieldConfigRepository.getByOwner(owner);
	}

	public Map<String, FieldConfig> getFieldConfigsMap(String owner) {
		return this.getByOwner(owner).stream().collect(Collectors.toMap(FieldConfig::getName, Function.identity()));
	}
}
