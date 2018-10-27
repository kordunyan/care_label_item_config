package com.itemconfiguration.controller;

import com.itemconfiguration.dao.resultobjects.InstructionLanguage;
import com.itemconfiguration.domain.InstructionTypeInputConfig;
import com.itemconfiguration.domain.Language;
import com.itemconfiguration.service.InstructionTypeInputConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instruction-input-config")
public class InstructionTypeInputConfigController {

	private InstructionTypeInputConfigService instructionTypeInputConfigService;

	public InstructionTypeInputConfigController(InstructionTypeInputConfigService instructionTypeInputConfigService) {
		this.instructionTypeInputConfigService = instructionTypeInputConfigService;
	}

	@GetMapping("/id/{id}")
	public InstructionTypeInputConfig getById(@PathVariable("id") Long id) {
		return instructionTypeInputConfigService.fidById(id).orElse(null);
	}

	@GetMapping("/instruction-languages")
	public Map<String, List<Language>> getInstructionLanguages() {
		return instructionTypeInputConfigService.getInstructionLanguages();
	}

}
