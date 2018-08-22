package com.itemconfiguration.export.bilder.line;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.export.bilder.BuilderUtils;
import org.springframework.stereotype.Component;

@Component
public class ItemFieldConfigLineBuilder {

	private static final String INSERT_LINE_FORAMAT = "(%-10s%-40s%-20s%-13s%-15s%-20s%-15s%-25s%-17s%-20s)";

	private static final String ITEM_ID = "item_id,";
	private static final String FIELD_CONFIG_NAME = "field_config_name,";
	private static final String DATA_SOURCE_NAME = "data_source_name,";
	private static final String IS_ACTIVE = "is_active,";
	private static final String IS_REQUIRED = "is_required,";
	private static final String PREDEFINED_VALUE = "predefined_value,";
	private static final String IS_EDITABLE = "is_editable,";
	private static final String STORE_LAST_USER_INPUT = "store_last_user_input,";
	private static final String CAN_ADD_LATER = "can_add_later,";
	private static final String FILTER_REGEX = "filter_regex,";

	public String buildFieldNames() {
		return String.format(INSERT_LINE_FORAMAT,
				ITEM_ID, FIELD_CONFIG_NAME, DATA_SOURCE_NAME, IS_ACTIVE, IS_REQUIRED,
				PREDEFINED_VALUE, IS_EDITABLE, STORE_LAST_USER_INPUT, CAN_ADD_LATER,
				FILTER_REGEX);
	}

	public String buildItemFieldConfigLine(ItemFieldConfig itemFieldConfig, Item item) {
		return String.format(INSERT_LINE_FORAMAT,
				BuilderUtils.escapeWithComma(item.getId()),
				BuilderUtils.escapeWithComma(itemFieldConfig.getFieldConfigName()),
				BuilderUtils.escapeWithComma(itemFieldConfig.getDataSourceName()),
				BuilderUtils.escapeWithComma(itemFieldConfig.isActive()),
				BuilderUtils.escapeWithComma(itemFieldConfig.isRequired()),
				BuilderUtils.escapeWithComma(itemFieldConfig.getPredefinedValue()),
				BuilderUtils.escapeWithComma(itemFieldConfig.isEditable()),
				BuilderUtils.escapeWithComma(itemFieldConfig.isStoreLastUserInput()),
				BuilderUtils.escapeWithComma(itemFieldConfig.isCanAddLater()),
				BuilderUtils.escapeValue(itemFieldConfig.getFilterRegex())
		);
	}

}
