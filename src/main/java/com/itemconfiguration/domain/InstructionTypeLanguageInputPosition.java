package com.itemconfiguration.domain;

import javax.persistence.*;

@Entity
public class InstructionTypeLanguageInputPosition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(
			name = "language_code",
			foreignKey = @ForeignKey(name = "instruction_type_language_input_position_language_code_fkey")
	)
	private Language language;

	private Integer inputPosition;

	private Boolean isRequired;

	private Boolean isWarning;

	private String expectedFontName;

	private Double expectedFontSize;

	private String valuePattern;

	private String columnName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Integer getInputPosition() {
		return inputPosition;
	}

	public void setInputPosition(Integer inputPosition) {
		this.inputPosition = inputPosition;
	}

	public Boolean getRequired() {
		return isRequired;
	}

	public void setRequired(Boolean required) {
		isRequired = required;
	}

	public Boolean getWarning() {
		return isWarning;
	}

	public void setWarning(Boolean warning) {
		isWarning = warning;
	}

	public String getExpectedFontName() {
		return expectedFontName;
	}

	public void setExpectedFontName(String expectedFontName) {
		this.expectedFontName = expectedFontName;
	}

	public Double getExpectedFontSize() {
		return expectedFontSize;
	}

	public void setExpectedFontSize(Double expectedFontSize) {
		this.expectedFontSize = expectedFontSize;
	}

	public String getValuePattern() {
		return valuePattern;
	}

	public void setValuePattern(String valuePattern) {
		this.valuePattern = valuePattern;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}
