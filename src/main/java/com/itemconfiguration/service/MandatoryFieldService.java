package com.itemconfiguration.service;

import com.itemconfiguration.dao.MandatoryFieldRepository;
import com.itemconfiguration.domain.MandatoryField;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MandatoryFieldService {

	private MandatoryFieldRepository mandatoryFieldRepository;

	public MandatoryFieldService(MandatoryFieldRepository mandatoryFieldRepository) {
		this.mandatoryFieldRepository = mandatoryFieldRepository;
	}

	public void save(MandatoryField mandatoryField) {
		mandatoryFieldRepository.save(mandatoryField);
	}

	public void saveAll(List<MandatoryField> mandatoryFields) {
		mandatoryFieldRepository.saveAll(mandatoryFields);
	}

	public void delete(MandatoryField mandatoryField) {
		mandatoryFieldRepository.delete(mandatoryField);
	}
}
