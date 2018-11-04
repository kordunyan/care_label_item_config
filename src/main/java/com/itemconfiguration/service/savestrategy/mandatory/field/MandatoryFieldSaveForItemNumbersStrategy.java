package com.itemconfiguration.service.savestrategy.mandatory.field;

import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.domain.MandatoryField;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.ItemFieldConfigService;
import com.itemconfiguration.service.MandatoryFieldService;
import com.itemconfiguration.service.savestrategy.mandatory.AbstractMandatoryDataSaveForItemNumbersStrategy;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("item-numbers-mandatory-fields-save")
public class MandatoryFieldSaveForItemNumbersStrategy extends AbstractMandatoryDataSaveForItemNumbersStrategy {

	private final MandatoryFieldService mandatoryFieldService;

	public MandatoryFieldSaveForItemNumbersStrategy(MandatoryFieldService mandatoryFieldService,
			ItemFieldConfigService itemFieldConfigService) {
		super(itemFieldConfigService);
		this.mandatoryFieldService = mandatoryFieldService;
	}

	@Override
	protected void saveNewData(SaveMandatoryDataDto dto, Map<String, List<ItemFieldConfig>> allItemFieldConfigs) {
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
