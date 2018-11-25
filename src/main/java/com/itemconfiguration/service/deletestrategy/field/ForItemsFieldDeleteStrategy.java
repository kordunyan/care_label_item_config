package com.itemconfiguration.service.deletestrategy.field;

import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.ItemWithFieldsMap;
import com.itemconfiguration.dto.ItemFieldCrudOperationsDto;
import com.itemconfiguration.service.FieldService;
import com.itemconfiguration.service.ItemService;
import com.itemconfiguration.utils.ItemUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

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
        List<Item> result = itemService.findAllByItemNumbers(dto.getItemNumbers());
        return ItemUtils.convertToItemsWithFieldsMap(ItemUtils.filterByMultipleFields(result,
                dto.getItemFieldsCriteria().getMultipleFields()));
    }

    private List<Field> getFieldsToDelete(ItemFieldCrudOperationsDto dto, List<ItemWithFieldsMap> itemWithFieldsMaps) {
       List<Field> result = new ArrayList<>();
        for (Field field : dto.getFields()) {
            for (ItemWithFieldsMap item : itemWithFieldsMaps) {
                if (item.hasField(field.getFieldConfigName())) {
                    result.add(item.getField(field.getFieldConfigName()));
                }
            }
        }
        return result;
    }
}
