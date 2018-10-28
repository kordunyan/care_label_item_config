package com.itemconfiguration.controller;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.MandatoryTranslationService;
import com.itemconfiguration.service.deletestrategy.mandatory.translation.MandatoryTranslationDeleteStrategyProvider;
import com.itemconfiguration.service.savestrategy.mandatory.translation.MandatoryTranslationSaveStrategyProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mandatory/translation")
public class MandatoryTranslationController {

	private MandatoryTranslationService mandatoryTranslationService;
	private MandatoryTranslationSaveStrategyProvider saveStrategyProvider;
	private MandatoryTranslationDeleteStrategyProvider deleteStrategyProvider;

	public MandatoryTranslationController(MandatoryTranslationService mandatoryTranslationService,
			MandatoryTranslationSaveStrategyProvider saveStrategyProvider,
			MandatoryTranslationDeleteStrategyProvider deleteStrategyProvider) {
		this.mandatoryTranslationService = mandatoryTranslationService;
		this.saveStrategyProvider = saveStrategyProvider;
		this.deleteStrategyProvider = deleteStrategyProvider;
	}

	@PostMapping("/save")
	public List<ItemFieldConfig> saveNew(@RequestBody()SaveMandatoryDataDto saveMandatoryDataDto) {
		return saveStrategyProvider.getSaveStrategy(saveMandatoryDataDto).save(saveMandatoryDataDto);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteTranslations(@RequestBody DeleteMandatoryDataDto dto) {
		deleteStrategyProvider.getStrategy(dto).delete(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
