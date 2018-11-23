package com.itemconfiguration.service.savestrategy.mandatory.field;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.MandatoryFieldService;
import com.itemconfiguration.service.savestrategy.mandatory.MandatoryDataSaver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component("MandatoryFieldsForItemNumbersSaver")
public class MandatoryFieldsForItemNumbersSaver implements MandatoryDataSaver {

	private final MandatoryFieldService mandatoryFieldService;

	public MandatoryFieldsForItemNumbersSaver(MandatoryFieldService mandatoryFieldService) {
		this.mandatoryFieldService = mandatoryFieldService;
	}

	public void saveNewData(SaveMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
		List<MandatoryField> newMandatoryFields = createNewMandatoryFields(dto.getItemFieldConfigs(), allItemFieldConfigs);
		mandatoryFieldService.saveAll(newMandatoryFields);
	}

	private List<MandatoryField> createNewMandatoryFields(List<ItemFieldConfig> originalItemFieldConfigs,
			Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
		List<MandatoryField> result = new ArrayList<>();
		for (ItemFieldConfig originalItemFieldConfig : originalItemFieldConfigs) {
			List<MandatoryField> newMandatoryFields = originalItemFieldConfig.getNewMandatoryFields();
			List<ItemFieldConfig> itemNumbersFieldConfigs = allItemFieldConfigs.get(originalItemFieldConfig.getFieldConfig().getName());
			createMandatoryFieldsForItemFieldConfigs(newMandatoryFields, itemNumbersFieldConfigs, originalItemFieldConfig.getId(),
					result);
			newMandatoryFields.forEach(mandatoryField -> mandatoryField.setItemFieldConfig(originalItemFieldConfig));
			result.addAll(newMandatoryFields);
		}
		return result;
	}

	private void createMandatoryFieldsForItemFieldConfigs(List<MandatoryField> newMandatoryFields,
			List<ItemFieldConfig> itemNumbersFieldConfigs, Long id, List<MandatoryField> result) {
		for (ItemFieldConfig itemFieldConfig : itemNumbersFieldConfigs) {
			if (itemFieldConfig.getId().equals(id)) {
				continue;
			}
			result.addAll(createNewMandatoryFieldsForItemFieldConfig(newMandatoryFields, itemFieldConfig));
		}
	}

	private List<MandatoryField> createNewMandatoryFieldsForItemFieldConfig(List<MandatoryField> newMandatoryFields,
			ItemFieldConfig itemFieldConfig) {
		List<MandatoryField> result = new ArrayList<>();
		for (MandatoryField newMandatoryField : newMandatoryFields) {
			Optional<FieldConfig> first = itemFieldConfig.getMandatoryFields()
					.stream()
					.map(MandatoryField::getFieldConfig)
					.filter(fieldConfig -> fieldConfig.equals(newMandatoryField.getFieldConfig()))
					.findFirst();
			if (first.isPresent()) {
				continue;
			}
			result.add(new MandatoryField(itemFieldConfig, newMandatoryField.getFieldConfig()));
		}
		return result;
	}
}
