package com.itemconfiguration.service;

import com.itemconfiguration.dao.MandatoryTranslationRepository;
import com.itemconfiguration.domain.MandatoryTranslation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MandatoryTranslationService {

	private MandatoryTranslationRepository mandatoryTranslationRepository;

	public MandatoryTranslationService(MandatoryTranslationRepository mandatoryTranslationRepository) {
		this.mandatoryTranslationRepository = mandatoryTranslationRepository;
	}

	public List<MandatoryTranslation> saveAll(List<MandatoryTranslation> mandatoryTranslations) {
		return (List<MandatoryTranslation>) mandatoryTranslationRepository.saveAll(mandatoryTranslations);
	}

}
