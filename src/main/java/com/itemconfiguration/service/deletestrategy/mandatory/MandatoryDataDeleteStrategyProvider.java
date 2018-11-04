package com.itemconfiguration.service.deletestrategy.mandatory;

import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MandatoryDataDeleteStrategyProvider {

	@Resource(name = "default-mandatory-translation-delete")
	private MandatoryDataDeleteStrategy defaultDeleteTranslationsStrategy;

	@Resource(name = "mandatory-translation-delete-for_items")
	private MandatoryDataDeleteStrategy byItemsDeleteTranslationsStrategy;

	@Resource(name = "default-mandatory-field-delete")
	private MandatoryDataDeleteStrategy defaultDeleteFieldStrategy;

	@Resource(name = "by-items-mandatory-field-delete")
	private MandatoryDataDeleteStrategy byItemNumbersDeleteFieldStrategy;

	public MandatoryDataDeleteStrategy getDeleteTranslationStrategy(DeleteMandatoryDataDto dto) {
		if (dto.isDeleteForAll()) {
			return byItemsDeleteTranslationsStrategy;
		}
		return defaultDeleteTranslationsStrategy;
	}

	public MandatoryDataDeleteStrategy getDeleteFieldsStrategy(DeleteMandatoryDataDto dto) {
		if (dto.isDeleteForAll()) {
			return byItemNumbersDeleteFieldStrategy;
		}
		return defaultDeleteFieldStrategy;
	}
}
