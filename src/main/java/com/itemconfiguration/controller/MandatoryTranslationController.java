package com.itemconfiguration.controller;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.deletestrategy.mandatory.translation.MandatoryTranslationDeleteStrategyProvider;
import com.itemconfiguration.service.savestrategy.mandatory.MandatoryDataSaveStrategyProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mandatory/translation")
public class MandatoryTranslationController {

	private MandatoryDataSaveStrategyProvider saveStrategyProvider;
	private MandatoryTranslationDeleteStrategyProvider deleteStrategyProvider;

	public MandatoryTranslationController(MandatoryDataSaveStrategyProvider saveStrategyProvider,
			MandatoryTranslationDeleteStrategyProvider deleteStrategyProvider) {
		this.saveStrategyProvider = saveStrategyProvider;
		this.deleteStrategyProvider = deleteStrategyProvider;
	}

	@PostMapping("/save")
	public List<ItemFieldConfig> saveNew(@RequestBody()SaveMandatoryDataDto saveMandatoryDataDto) {
		return saveStrategyProvider.getTranslationSaveStrategy(saveMandatoryDataDto).save(saveMandatoryDataDto);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteTranslations(@RequestBody DeleteMandatoryDataDto dto) {
		deleteStrategyProvider.getStrategy(dto).delete(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
