package com.itemconfiguration.service.deletestrategy.field;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.dto.ItemFieldCrudOperationsDto;
import com.itemconfiguration.service.FieldService;
import com.itemconfiguration.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ForItemsFieldDeleteStrategy {

    private FieldService fieldService;
    private ItemService itemService;

    public ForItemsFieldDeleteStrategy(FieldService fieldService, ItemService itemService) {
        this.fieldService = fieldService;
        this.itemService = itemService;
    }

    public void delete(ItemFieldCrudOperationsDto dto) {
        if (!dto.hasItemNumbers()) {
            throw new IllegalArgumentException("[itemNumbers] should no be empty");
        }
        if (!dto.hasFields()) {
            throw new IllegalArgumentException("[fields] should no be empty");
        }
        List<ItemWithFieldsMap> itemWithFieldsMaps = getItems(dto);
        fieldService.deleteAll(getFieldsToDelete(dto, itemWithFieldsMaps));
    }

    private List<ItemWithFieldsMap> getItems(ItemFieldCrudOperationsDto dto) {
        return itemService.findItemsFithFieldMapByItemNumbers(dto.getItemNumbers());
    }

    private List<Field> getFieldsToDelete(ItemFieldCrudOperationsDto dto, List<ItemWithFieldsMap> itemWithFieldsMaps) {
       List<Field> result = new ArrayList<>();
        for (Field field : dto.getFields()) {
            for (ItemWithFieldsMap item : itemWithFieldsMaps) {
                if (item.containsField(field.getFieldConfigName())) {
                    result.add(item.getField(field.getFieldConfigName()));
                }
            }
        }
        return result;
    }
}
