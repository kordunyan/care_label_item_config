package com.itemconfiguration.service;

import com.itemconfiguration.dao.MandatoryTranslationRepository;
import org.springframework.stereotype.Service;

@Service
public class MandatoryTranslationService {

	private MandatoryTranslationRepository mandatoryTranslationRepository;

	public MandatoryTranslationService(MandatoryTranslationRepository mandatoryTranslationRepository) {
		this.mandatoryTranslationRepository = mandatoryTranslationRepository;
	}
}
