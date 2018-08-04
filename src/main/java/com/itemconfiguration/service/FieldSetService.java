package com.itemconfiguration.service;

import com.itemconfiguration.dao.FieldSetDAO;
import com.itemconfiguration.domain.FieldSet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldSetService {

	private FieldSetDAO fieldSetDAO;

	public FieldSetService(FieldSetDAO fieldSetDAO) {
		this.fieldSetDAO = fieldSetDAO;
	}

	public void save(FieldSet fieldSet) {
		fieldSetDAO.save(fieldSet);
	}

	public void saveAll(List<FieldSet> fieldSets) {
		fieldSetDAO.saveAll(fieldSets);
	}
}
