package com.itemconfiguration.export.bilder.block;

import com.itemconfiguration.domain.AppFields;
import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.FieldConfig;
import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.export.bilder.line.StaticLineBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemFieldsBlockBuilder {

    public List<String> build(ItemWithFieldsMap item, FieldConfigsWrapper fieldConfigsWrapper) {
        List<String> result = new ArrayList<>();
        result.add(createItemComment(item, fieldConfigsWrapper));
        return result;
    }

    private String createItemComment(ItemWithFieldsMap item, FieldConfigsWrapper fieldConfigsWrapper) {
        return new StringBuilder()
                .append("-- item: ")
                .append(item.getFieldValue(AppFields.D2COMM_ITEM_NUMBER))
                .append(StaticLineBuilder.WHITE_SPACE)
                .append(createMultipleFieldsComment(item, fieldConfigsWrapper))
                .append(StaticLineBuilder.NEW_LINE)
                .toString();
    }

    private String createMultipleFieldsComment(ItemWithFieldsMap item, FieldConfigsWrapper fieldConfigsWrapper) {
        List<String> result = new ArrayList<>();
        fieldConfigsWrapper.getMultipleFieldConfigs()
                .forEach(fieldConfig -> {
                    if (item.containsField(fieldConfig.getName())) {
                        result.add(toCommentField(item.getField(fieldConfig.getName())));
                    }
                });
        return StringUtils.join(result, ", ");
    }

    private String toCommentField(Field field) {
        return field.getFieldConfigName() + " : " + field.getValue();
    }

}
