package com.itemconfiguration.service;

import com.itemconfiguration.dao.MandatoryFieldRepository;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.domain.MandatoryTranslation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MandatoryFieldService {

	private MandatoryFieldRepository mandatoryFieldRepository;

	public MandatoryFieldService(MandatoryFieldRepository mandatoryFieldRepository) {
		this.mandatoryFieldRepository = mandatoryFieldRepository;
	}

	public void save(MandatoryField mandatoryField) {
		mandatoryFieldRepository.save(mandatoryField);
	}

	public void saveAll(List<MandatoryField> mandatoryFields) {
		mandatoryFieldRepository.saveAll(mandatoryFields);
	}

	public void delete(MandatoryField mandatoryField) {
		mandatoryFieldRepository.delete(mandatoryField);
	}

	@Transactional
	public void deleteALl(List<MandatoryField> mandatoryFields) {
		if (CollectionUtils.isNotEmpty(mandatoryFields)) {
			this.mandatoryFieldRepository.deleteByIds(getFieldsIds(mandatoryFields));
		}
	}

	private List<Long> getFieldsIds(List<MandatoryField> mandatoryFields) {
		return mandatoryFields.stream()
				.map(MandatoryField::getId)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}
}
