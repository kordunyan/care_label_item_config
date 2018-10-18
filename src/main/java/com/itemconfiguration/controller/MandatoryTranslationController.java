package com.itemconfiguration.controller;

import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.MandatoryTranslationService;
import com.itemconfiguration.service.savestrategy.mandatorytranslation.MandatoryTranslationSaveStrategyProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mandatory/translation")
public class MandatoryTranslationController {

	private MandatoryTranslationService mandatoryTranslationService;
	private MandatoryTranslationSaveStrategyProvider mandatoryTranslationSaveStrategyProvider;

	public MandatoryTranslationController(MandatoryTranslationService mandatoryTranslationService,
			MandatoryTranslationSaveStrategyProvider mandatoryTranslationSaveStrategyProvider) {
		this.mandatoryTranslationService = mandatoryTranslationService;
		this.mandatoryTranslationSaveStrategyProvider = mandatoryTranslationSaveStrategyProvider;
	}

	@PostMapping("/save")
	public List<MandatoryTranslation> saveNew(@RequestBody()SaveMandatoryDataDto saveMandatoryDataDto) {
		return mandatoryTranslationSaveStrategyProvider.getSaveStrategy(saveMandatoryDataDto).save(saveMandatoryDataDto);
	}

}
