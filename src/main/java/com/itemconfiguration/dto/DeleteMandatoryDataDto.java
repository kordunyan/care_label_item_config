package com.itemconfiguration.dto;

import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.domain.MandatoryTranslation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteMandatoryDataDto {
	private Map<String, List<MandatoryTranslation>> translationsToDeleteByFieldName = new HashMap<>();
	private Map<String, List<MandatoryField>> fieldsToDeleteByFieldName = new HashMap<>();
	private boolean deleteForAll;
	private List<String> itemNumbers = new ArrayList<>();

	public Map<String, List<MandatoryTranslation>> getTranslationsToDeleteByFieldName() {
		return translationsToDeleteByFieldName;
	}

	public void setTranslationsToDeleteByFieldName(Map<String, List<MandatoryTranslation>> translationsToDeleteByFieldName) {
		this.translationsToDeleteByFieldName = translationsToDeleteByFieldName;
	}

	public Map<String, List<MandatoryField>> getFieldsToDeleteByFieldName() {
		return fieldsToDeleteByFieldName;
	}

	public void setFieldsToDeleteByFieldName(Map<String, List<MandatoryField>> fieldsToDeleteByFieldName) {
		this.fieldsToDeleteByFieldName = fieldsToDeleteByFieldName;
	}

	public boolean isDeleteForAll() {
		return deleteForAll;
	}

	public void setDeleteForAll(boolean deleteForAll) {
		this.deleteForAll = deleteForAll;
	}

	public List<String> getItemNumbers() {
		return itemNumbers;
	}

	public void setItemNumbers(List<String> itemNumbers) {
		this.itemNumbers = itemNumbers;
	}
}
