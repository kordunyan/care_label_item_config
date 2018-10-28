package com.itemconfiguration.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class MandatoryTranslation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(
			name = "item_field_config_id",
			foreignKey = @ForeignKey(name = "mandatory_translation_item_field_config_id_fkey")
	)
	@JsonIgnore
	private ItemFieldConfig itemFieldConfig;

	@ManyToOne
	@JoinColumn(
			name = "language_code",
			foreignKey = @ForeignKey(name = "mandatory_translation_language_code_fkey")
	)
	private Language language;

	public MandatoryTranslation() {
	}

	public MandatoryTranslation(Language language) {
		this.language = language;
	}

	public MandatoryTranslation(ItemFieldConfig itemFieldConfig, Language language) {
		this.itemFieldConfig = itemFieldConfig;
		this.language = language;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemFieldConfig getItemFieldConfig() {
		return itemFieldConfig;
	}

	public void setItemFieldConfig(ItemFieldConfig itemFieldConfig) {
		this.itemFieldConfig = itemFieldConfig;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
}
