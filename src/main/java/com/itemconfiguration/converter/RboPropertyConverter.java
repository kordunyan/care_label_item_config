package com.itemconfiguration.converter;

import com.itemconfiguration.config.properties.RboProperty;
import com.itemconfiguration.dto.RboDto;

public class RboPropertyConverter {

	public static RboDto convert(RboProperty rboProperty) {
		return new RboDto(rboProperty.getName(), rboProperty.getCode());
	}

}
