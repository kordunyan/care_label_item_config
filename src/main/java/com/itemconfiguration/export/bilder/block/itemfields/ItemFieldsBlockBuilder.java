package com.itemconfiguration.export.bilder.block.itemfields;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.export.bilder.line.FieldLineBuilder;
import com.itemconfiguration.export.bilder.line.StaticLines;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemFieldsBlockBuilder {
    private ItemCommentBuilder itemCommentBuilder;
    private FieldLineBuilder fieldLineBuilder;

    public ItemFieldsBlockBuilder(ItemCommentBuilder itemCommentBuilder, FieldLineBuilder fieldLineBuilder) {
        this.itemCommentBuilder = itemCommentBuilder;
        this.fieldLineBuilder = fieldLineBuilder;
    }

    public List<String> build(ItemWithFieldsMap item, FieldConfigsWrapper fieldConfigsWrapper) {
        List<String> result = new ArrayList<>();
        result.add(itemCommentBuilder.build(item, fieldConfigsWrapper));
        result.add(buildWithNewFieldSetId());
        if (item.hasFields()) {
            result.add(getInsertIntoFields());
            result.add(buildFieldLines(item.getItem().getFields()));
        }
        return result;
    }

    private String buildFieldLines(List<Field> fields) {
        return fields.stream()
                .map(field -> fieldLineBuilder.build(field))
                .collect(Collectors.joining(StaticLines.INSERT_VALUES_SEPARATOR)) + ";";
    }

    private String getInsertIntoFields() {
        return "INSERT INTO field (field_config_name, value, field_set_id) VALUES" + StaticLines.NEW_LINE;
    }

    private String buildWithNewFieldSetId() {
        return "WITH new_field_set_id AS (INSERT INTO field_set DEFAULT VALUES RETURNING id) SELECT id INTO field_set_id FROM new_field_set_id;" + StaticLines.NEW_LINE;
    }
}
