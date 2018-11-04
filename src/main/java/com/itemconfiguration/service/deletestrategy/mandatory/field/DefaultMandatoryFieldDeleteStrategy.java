package com.itemconfiguration.service.deletestrategy.mandatory.field;

import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.service.MandatoryFieldService;
import com.itemconfiguration.service.deletestrategy.mandatory.MandatoryDataDeleteStrategy;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("default-mandatory-field-delete")
public class DefaultMandatoryFieldDeleteStrategy implements MandatoryDataDeleteStrategy {

	private final MandatoryFieldService mandatoryFieldService;

	public DefaultMandatoryFieldDeleteStrategy(MandatoryFieldService mandatoryFieldService) {
		this.mandatoryFieldService = mandatoryFieldService;
	}

	@Override
	public void delete(DeleteMandatoryDataDto dto) {
		if (MapUtils.isEmpty(dto.getFieldsToDeleteByFieldName())) {
			throw new IllegalArgumentException("[fieldsToDeleteByFieldName] param should not by empty");
		}
		List<MandatoryField> mandatoryFieldsToDelete = getFieldsToDelete(dto.getFieldsToDeleteByFieldName());
		mandatoryFieldService.deleteALl(mandatoryFieldsToDelete);
	}

	private List<MandatoryField> getFieldsToDelete(Map<String, List<MandatoryField>> fieldsToDeleteByFieldName) {
		return fieldsToDeleteByFieldName.keySet()
				.stream()
				.flatMap(fieldName -> fieldsToDeleteByFieldName.get(fieldName).stream())
				.filter(mandatoryField -> Objects.nonNull(mandatoryField.getId()))
				.collect(Collectors.toList());
	}
}
