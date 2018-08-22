package com.itemconfiguration.export.bilder.line;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.export.bilder.BuilderUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class FieldLineBuilder {

	public String build(Field field) {
		return String.format("(%-45s %-30s field_set_id)",
				BuilderUtils.escapeWithComma(field.getFieldConfigName()),
				BuilderUtils.escapeWithComma(field.getValue()));
	}

}
