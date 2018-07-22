package com.kordunyan.service;

import com.kordunyan.dao.FieldDAO;
import com.kordunyan.domain.Field;
import com.kordunyan.domain.FieldSet;
import com.kordunyan.dto.FieldForAllItemsDto;
import com.kordunyan.dto.NewFieldsDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldService {

	private FieldDAO fieldDAO;

	public FieldService(FieldDAO fieldDAO) {
		this.fieldDAO = fieldDAO;
	}

	public void saveField(Field field) {
		fieldDAO.save(field);
	}

	public void saveAll(List<Field> fields) {
		fieldDAO.saveAll(fields);
	}

	public List<String> findByFieldConfigName(String fieldConfigName) {
		return fieldDAO.findAllByFieldConfigname(fieldConfigName);
	}

	public Field update(Field field) {
		return this.fieldDAO.save(field);
	}

	public int updateAllForItem(Field field) {
		return this.fieldDAO.updateAllForItem(field.getValue(), field.getId());
	}

	public void saveNewFieldsForAllItems(List<FieldForAllItemsDto> fieldForAllItems) {
		this.fieldDAO.saveAll(createCopyForEachFieldSets(fieldForAllItems));
	}

	private List<Field> createCopyForEachFieldSets(List<FieldForAllItemsDto> fieldForAllItems) {
		List<Field> result = new ArrayList<>();
		for (FieldForAllItemsDto fieldForAllItem : fieldForAllItems) {
			List<Field> itemFields = fieldForAllItem.getFieldSets().stream()
					.map(fieldSet -> {
						Field field = new Field(fieldForAllItem.getField());
						field.setFieldSet(fieldSet);
						return field;
					})
					.collect(Collectors.toList());
			result.addAll(itemFields);
		}
		return result;
	}

	public int deleteForAllItems(Field field) {
		return this.fieldDAO.deleteForAllItems(field.getId());
	}

	public void delete(Field field) {
		this.fieldDAO.delete(field);
	}
}
