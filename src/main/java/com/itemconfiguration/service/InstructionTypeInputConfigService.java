package com.itemconfiguration.service;

import com.itemconfiguration.dao.InstructionTypeInputConfigRepository;
import com.itemconfiguration.dao.resultobjects.InstructionLanguage;
import com.itemconfiguration.domain.DataType;
import com.itemconfiguration.domain.InstructionTypeInputConfig;
import com.itemconfiguration.domain.Language;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.*;

@Service
public class InstructionTypeInputConfigService {

	private InstructionTypeInputConfigRepository instructionTypeInputConfigRepository;

	public InstructionTypeInputConfigService(InstructionTypeInputConfigRepository instructionTypeInputConfigRepository) {
		this.instructionTypeInputConfigRepository = instructionTypeInputConfigRepository;
	}

	public Optional<InstructionTypeInputConfig> fidById(Long id) {
		return instructionTypeInputConfigRepository.findById(id);
	}

	public Map<String, List<Language>> getInstructionLanguages() {
		List<InstructionLanguage> istructionLanguages = instructionTypeInputConfigRepository.getIstructionLanguages();
		Map<String, List<Language>> result = new HashMap<>();
		for (InstructionLanguage instructionLanguage : istructionLanguages) {
			DataType dataType = instructionLanguage.getInstructionType();
			if (!result.containsKey(dataType.getName())) {
				result.put(dataType.getName(), new ArrayList<>());
			}
			result.get(dataType.getName()).add(instructionLanguage.getLanguage());
		}
		this.sortLanguages(result);
		return result;
	}

	private void sortLanguages(Map<String, List<Language>> instructionLanguages) {
		for (String instructionName : instructionLanguages.keySet()) {
			instructionLanguages.get(instructionName).sort((l1, l2) -> l1.getName().compareToIgnoreCase(l2.getName()));
		}

	}
}
