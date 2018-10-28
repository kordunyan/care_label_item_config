package com.itemconfiguration.service.deletestrategy.mandatory.translation;

import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MandatoryTranslationDeleteStrategyProvider {

	@Resource(name = "default-mandatory-translation-delete")
	private MandatoryTranslationDeleteStrategy defaultStrategy;

	@Resource(name = "mandatory-translation-delete-for_items")
	private MandatoryTranslationDeleteStrategy byItemsStrategy;

	public MandatoryTranslationDeleteStrategy getStrategy(DeleteMandatoryDataDto dto) {
		if (dto.isDeleteForAll()) {
			return byItemsStrategy;
		}
		return defaultStrategy;
	}

}
