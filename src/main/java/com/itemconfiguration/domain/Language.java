package com.itemconfiguration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Language {
	@Id
	private String code;
	@Column(nullable = false)
	private String name;
	@Column(name = "print_code", nullable = false)
	private String printCode;

	public Language() {
	}

	public Language(String code, String name, String printCode) {
		this.code = code;
		this.name = name;
		this.printCode = printCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrintCode() {
		return printCode;
	}

	public void setPrintCode(String printCode) {
		this.printCode = printCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Language language = (Language) o;

		if (code != null ? !code.equals(language.code) : language.code != null) return false;
		if (name != null ? !name.equals(language.name) : language.name != null) return false;
		return printCode != null ? printCode.equals(language.printCode) : language.printCode == null;
	}

	@Override
	public int hashCode() {
		int result = code != null ? code.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (printCode != null ? printCode.hashCode() : 0);
		return result;
	}
}
