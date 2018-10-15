package com.itemconfiguration.service;

import com.itemconfiguration.dao.LanguageRepository;
import com.itemconfiguration.domain.Language;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

	private LanguageRepository languageRepository;

	public LanguageService(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}

	public void save(Language language) {
		languageRepository.save(language);
	}

	public void saveALl(List<Language> languages) {
		languageRepository.saveAll(languages);
	}

	public Iterable<Language> findAll() {
		return languageRepository.findAllOrderByName();
	}
}
