package com.itemconfiguration.export.bilder.block.item;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.export.bilder.line.StaticLines;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemBlockBuilder {

	public List<String> build(ItemWithFieldsMap item) {
		List<String> result = new ArrayList<>();
		result.add(buildInsertIntoItem());
		result.add(buildItemValues(item.getItem()));
		return result;
	}

	private String buildItemValues(Item item) {
		return String.format("(%d, %s, %s, field_set_id);", item.getId(), item.isIpps(), item.isSb());
	}

	private String buildInsertIntoItem() {
		return new StringBuilder()
				.append("INSERT INTO item").append(StaticLines.NEW_LINE)
				.append("(id, is_ipps, is_sb, field_set_id) VALUES").append(StaticLines.NEW_LINE)
				.toString();
	}
}
