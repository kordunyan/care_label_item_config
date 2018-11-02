package com.itemconfiguration.controller;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.savestrategy.mandatory.MandatoryDataSaveStrategyProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mandatory/field")
public class MandatoryFieldController {

	private MandatoryDataSaveStrategyProvider saveStrategyProvider;

	public MandatoryFieldController(MandatoryDataSaveStrategyProvider saveStrategyProvider) {
		this.saveStrategyProvider = saveStrategyProvider;
	}

	@PostMapping("/save")
	public List<ItemFieldConfig> saveNew(@RequestBody() SaveMandatoryDataDto saveMandatoryDataDto) {
		return saveStrategyProvider.getFieldsSaveStrategy(saveMandatoryDataDto).save(saveMandatoryDataDto);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteTranslations(@RequestBody DeleteMandatoryDataDto dto) {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
