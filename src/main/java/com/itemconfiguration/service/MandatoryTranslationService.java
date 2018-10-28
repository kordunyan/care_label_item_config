package com.itemconfiguration.service;

import com.itemconfiguration.dao.MandatoryTranslationRepository;
import com.itemconfiguration.domain.MandatoryTranslation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MandatoryTranslationService {

	private MandatoryTranslationRepository mandatoryTranslationRepository;

	public MandatoryTranslationService(MandatoryTranslationRepository mandatoryTranslationRepository) {
		this.mandatoryTranslationRepository = mandatoryTranslationRepository;
	}

	public List<MandatoryTranslation> saveAll(List<MandatoryTranslation> mandatoryTranslations) {
		return (List<MandatoryTranslation>) mandatoryTranslationRepository.saveAll(mandatoryTranslations);
	}

	@Transactional
	public void deleteALl(List<MandatoryTranslation> mandatoryTranslations) {
		this.mandatoryTranslationRepository.deleteByIds(getTranslationIds(mandatoryTranslations));
	}

	private List<Long> getTranslationIds(List<MandatoryTranslation> mandatoryTranslations) {
		return mandatoryTranslations.stream()
				.map(MandatoryTranslation::getId)
				.collect(Collectors.toList());
	}

}
