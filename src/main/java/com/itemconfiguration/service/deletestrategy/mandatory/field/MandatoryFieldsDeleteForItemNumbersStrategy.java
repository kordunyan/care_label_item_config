package com.itemconfiguration.service.deletestrategy.mandatory.field;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.MandatoryFieldService;
import com.itemconfiguration.service.deletestrategy.mandatory.AbstractMandatoryDataDeleteForItemNumbersStrategy;
import com.itemconfiguration.utils.DomainUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component("by-items-mandatory-field-delete")
public class MandatoryFieldsDeleteForItemNumbersStrategy extends AbstractMandatoryDataDeleteForItemNumbersStrategy {

	private final MandatoryFieldService mandatoryFieldService;

	public MandatoryFieldsDeleteForItemNumbersStrategy(ItemFieldConfigService itemFieldConfigService,
			MandatoryFieldService mandatoryFieldService) {
		super(itemFieldConfigService);
		this.mandatoryFieldService = mandatoryFieldService;
	}

	@Override
	protected void deleteMandatoryData(DeleteMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
		if (MapUtils.isEmpty(dto.getFieldsToDeleteByFieldName())) {
			throw new IllegalArgumentException("[fieldsToDeleteByFieldName] param should not by empty");
		}
		List<MandatoryField> fieldToDelete = getMandatoryFieldsToDelete(dto.getFieldsToDeleteByFieldName(), allItemFieldConfigs);
		mandatoryFieldService.deleteALl(fieldToDelete);
	}

	private List<MandatoryField> getMandatoryFieldsToDelete(Map<String, List<MandatoryField>> fieldsToDeleteByFieldName,
			Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
		List<MandatoryField> result = new ArrayList<>();
		for (String fieldConfigName : fieldsToDeleteByFieldName.keySet()) {
			Set<FieldConfig> fieldsToDelete = DomainUtils.getMandatoryFieldConfigs(fieldsToDeleteByFieldName.get(fieldConfigName));
			List<MandatoryField> toDelete = allItemFieldConfigs.get(fieldConfigName)
					.stream()
					.flatMap(itemFieldConfig -> itemFieldConfig.getMandatoryFields().stream())
					.filter(mandatoryField -> fieldsToDelete.contains(mandatoryField.getFieldConfig()))
					.collect(Collectors.toList());
			result.addAll(toDelete);
		}
		return result;
	}

	@Override
	protected List<String> getItemFieldConfigNames(DeleteMandatoryDataDto dto) {
		return new ArrayList<>(dto.getFieldsToDeleteByFieldName().keySet());
	}
}
