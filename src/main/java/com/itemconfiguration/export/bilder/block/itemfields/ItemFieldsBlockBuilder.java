package com.itemconfiguration.export.bilder.block.itemfields;

import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.export.bilder.line.StaticLineBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemFieldsBlockBuilder {

    private ItemCommentBuilder itemCommentBuilder;

    public ItemFieldsBlockBuilder(ItemCommentBuilder itemCommentBuilder) {
        this.itemCommentBuilder = itemCommentBuilder;
    }

    public List<String> build(ItemWithFieldsMap item, FieldConfigsWrapper fieldConfigsWrapper) {
        List<String> result = new ArrayList<>();
        result.add(itemCommentBuilder.build(item, fieldConfigsWrapper));
        result.add(StaticLineBuilder.getWithNewFieldsSetId());
        return result;
    }


}
