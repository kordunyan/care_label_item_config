package com.itemconfiguration.service;

import com.itemconfiguration.dao.MandatoryFieldRepository;
import com.itemconfiguration.domain.MandatoryField;
import org.springframework.stereotype.Service;

@Service
public class MandatoryFieldService {

	private MandatoryFieldRepository mandatoryFieldRepository;

	public MandatoryFieldService(MandatoryFieldRepository mandatoryFieldRepository) {
		this.mandatoryFieldRepository = mandatoryFieldRepository;
	}

	public void save(MandatoryField mandatoryField) {
		mandatoryFieldRepository.save(mandatoryField);
	}

	public void delete(MandatoryField mandatoryField) {
		mandatoryFieldRepository.delete(mandatoryField);
	}
}
