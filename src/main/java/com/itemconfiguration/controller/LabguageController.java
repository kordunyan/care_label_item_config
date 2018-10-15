package com.itemconfiguration.controller;

import com.itemconfiguration.domain.Language;
import com.itemconfiguration.service.LanguageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/language")
public class LabguageController {

	private LanguageService languageService;

	public LabguageController(LanguageService languageService) {
		this.languageService = languageService;
	}

	@GetMapping("/save-all")
	public List<Language> save() {
		List<Language> languages = new ArrayList<>();
		languages.add(new Language("ar_SA", "Arabic", "AE"));
		languages.add(new Language("cn_CNs", "Simplified Chinese", "CN simpl."));
		languages.add(new Language("cn_CNt", "Traditional Chinese", "CN trad."));
		languages.add(new Language("cs_CZ", "Czech", "CZ"));
		languages.add(new Language("da_DK", "Danish", "DK"));
		languages.add(new Language("de_DE", "German", "DE"));
		languages.add(new Language("el_GR", "Greek", "GR"));
		languages.add(new Language("en_EN", "English", "EN"));
		languages.add(new Language("es_ES", "Spanish", "ES"));
		languages.add(new Language("fr_FR", "French", "FR"));
		languages.add(new Language("id_ID", "Indonesia", "ID"));
		languages.add(new Language("it_IT", "Italia", "IT"));
		languages.add(new Language("jp_JP", "Japanese", "JP"));
		languages.add(new Language("ko_KR", "Korean", "KR"));
		languages.add(new Language("nl_NL", "Dutch", "NL"));
		languages.add(new Language("no_NO", "Norwegian", "NO"));
		languages.add(new Language("pl_PL", "Polish", "PL"));
		languages.add(new Language("pt_BR", "Portugese (Brazilizn)", "PT"));
		languages.add(new Language("pt_PT", "Portugese", "PT"));
		languages.add(new Language("ru_RU", "Russian", "RU"));
		languages.add(new Language("se_SE", "Swedish", "SE"));
		languages.add(new Language("sk_SK", "Slovak", "SK"));
		languages.add(new Language("tr_TR", "Turkish", "TR"));
		languageService.saveALl(languages);
		return languages;
	}

	@GetMapping("/all")
	public Iterable<Language> findAll() {
		return languageService.findAll();
	}
}
