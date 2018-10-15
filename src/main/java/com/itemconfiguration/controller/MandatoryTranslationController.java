package com.itemconfiguration.controller;

import com.itemconfiguration.domain.MandatoryTranslation;
import com.itemconfiguration.service.MandatoryTranslationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/mandatory/translation")
public class MandatoryTranslationController {

	private MandatoryTranslationService mandatoryTranslationService;

	public MandatoryTranslationController(MandatoryTranslationService mandatoryTranslationService) {
		this.mandatoryTranslationService = mandatoryTranslationService;
	}
}
