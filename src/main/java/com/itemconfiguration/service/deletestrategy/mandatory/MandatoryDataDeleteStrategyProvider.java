package com.itemconfiguration.service.deletestrategy.mandatory;

import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MandatoryDataDeleteStrategyProvider {

	@Resource(name = "ForAllMandatoryDataDeleteStrategy")
	private MandatoryDataDeleteStrategy forAllSeleteStrategy;

	@Resource(name = "DefaultMandatoryDataDeleteStrategy")
	private MandatoryDataDeleteStrategy defaultDeleteStrategy;

	public MandatoryDataDeleteStrategy getDeleteFieldsStrategy(DeleteMandatoryDataDto dto) {
		if (dto.isDeleteForAll()) {
			return forAllSeleteStrategy;
		}
		return defaultDeleteStrategy;
	}
}
