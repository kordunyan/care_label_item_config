package com.itemconfiguration.domain;

import javax.persistence.*;

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
	private ItemFieldConfig itemFieldConfig;

	@OneToOne
	@JoinColumn(
			name = "language_code",
			foreignKey = @ForeignKey(name = "mandatory_translation_language_code_fkey")
	)
	private Language language;

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
