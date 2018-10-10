package com.itemconfiguration.controller;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.service.FieldConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/field_config")
public class FieldConfigController {

	private FieldConfigService fieldConfigService;

	public FieldConfigController(FieldConfigService fieldConfigService) {
		this.fieldConfigService = fieldConfigService;
	}

	@GetMapping("/add-test-fields")
	public List<FieldConfig> saveNew() {
		List<FieldConfig> fieldConfigs = new ArrayList<>();
		fieldConfigs.add(new FieldConfig("BRAND", "TEXT_FIELD", "ITEM", false, false));
		fieldConfigs.add(new FieldConfig("SEASON", "TEXT_FIELD", "ITEM", false, true));
		fieldConfigs.add(new FieldConfig("D2COMM_ITEM_NUMBER", "TEXT_FIELD", "ITEM", false, false));
		fieldConfigs.add(new FieldConfig("CLOTHING_TYPE", "TEXT_FIELD", "ITEM", false, true));
		fieldConfigs.add(new FieldConfig("CARE_CONFIG_ROTARY_FORMAT_GROUP", "TEXT_FIELD", "ITEM", false, false));
		fieldConfigs.add(new FieldConfig("ROTARY_PART_NUMBER", "TEXT_FIELD", "ITEM", false, false));
		fieldConfigs.add(new FieldConfig("THERMAL_PART_NUMBER", "TEXT_FIELD", "ITEM", false, false));
		fieldConfigs.add(new FieldConfig("ADD_BOOKLET_ROTARY_PART_NUMBER", "TEXT_FIELD", "ITEM", false, false));
		fieldConfigs.add(new FieldConfig("ADD_BOOKLET_THERMAL_PART_NUMBER", "TEXT_FIELD", "ITEM", false, false));
		fieldConfigs.add(new FieldConfig("ADD_STRAIGHT_CUT_ROTARY_PART_NUMBER", "TEXT_FIELD", "ITEM", false, false));
		fieldConfigs.add(new FieldConfig("ADD_STRAIGHT_CUT_THERMAL_PART_NUMBER", "TEXT_FIELD", "ITEM", false, false));
		fieldConfigs.add(new FieldConfig("IS_EQUALIZE_BLOCKS_LENGTH", "TEXT_FIELD", "ITEM", false, false));
		fieldConfigs.add(new FieldConfig("IS_STATIC_CONTENT", "TEXT_FIELD", "ITEM", false, false));
		fieldConfigs.add(new FieldConfig("CARE_CODE_SUB", "TEXT_FIELD", "LABEL", true, false));

		fieldConfigService.saveAll(fieldConfigs);
		return fieldConfigs;
	}

	@GetMapping("/owner/{owner}")
	public List<FieldConfig> getByOwner(@PathVariable("owner") String owner) {
		return fieldConfigService.getByOwner(owner);
	}

	@GetMapping("/owner/{owner}/map")
	public Map<String, FieldConfig> getFieldConfigsMap(@PathVariable("owner") String owner) {
		return fieldConfigService.getFieldConfigsMap(owner);
	}

	@GetMapping("/all")
	public List<FieldConfig> getAll() {
		return fieldConfigService.getAll();
	}

	@GetMapping("/instructions/fields")
	public Map<String, List<String>> getInstructionsFields() {
		return fieldConfigService.getInstructionFields();
	}

	@PostMapping("/save-all")
	public ResponseEntity<Void> saveAll(@RequestBody List<FieldConfig> fieldConfigs) {
		fieldConfigService.saveAll(fieldConfigs);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody FieldConfig fieldConfig) {
		fieldConfigService.delete(fieldConfig);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
