package com.itemconfiguration.service;

import com.itemconfiguration.dao.FieldDAO;
import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.FieldSet;
import com.itemconfiguration.dto.FieldForAllItemsDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

	public void saveNewFieldsForAllItems(List<FieldForAllItemsDto> fieldForAllItems) {
		this.fieldDAO.saveAll(createCopyForEachFieldSets(fieldForAllItems));
	}

	@Transactional
	public void deleteAll(List<Field> fields) {
		if (CollectionUtils.isEmpty(fields)) {
			return;
		}
		fieldDAO.deleteAllById(fields.stream().map(Field::getId).collect(Collectors.toList()));
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

	public int deleteByFieldSet(FieldSet fieldSet) {
		return fieldDAO.deleteByFieldSet(fieldSet);
	}

	public int deleteByFieldSets(List<FieldSet> fieldSets) {
		return fieldDAO.deleteByFieldSetIn(fieldSets);
	}

	public void delete(Field field) {
		this.fieldDAO.delete(field);
	}
}
