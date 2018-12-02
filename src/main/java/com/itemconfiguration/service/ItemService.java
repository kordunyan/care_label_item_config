package com.itemconfiguration.service;

import com.itemconfiguration.dao.ItemDAO;
import com.itemconfiguration.domain.AppFields;
import com.itemconfiguration.domain.Field;
import com.itemconfiguration.domain.FieldSet;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.UpdateLocationDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.itemconfiguration.utils.ItemUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class ItemService {

    private final ItemFieldConfigService itemFieldConfigService;
    private final FieldSetService fieldSetService;
    private final FieldService fieldService;
    private final ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO, FieldSetService fieldSetService, FieldService fieldDAO, ItemFieldConfigService itemFieldConfigService) {
        this.itemFieldConfigService = itemFieldConfigService;
        this.fieldSetService = fieldSetService;
        this.fieldService = fieldDAO;
        this.itemDAO = itemDAO;
    }

    public int updateLocationEnablemend(Item item) {
        return this.itemDAO.updateLocationEnablemend(item.isIpps(), item.isSb(), item.getId());
    }

    @Transactional
    public void updateLocationEnablemendForAll(UpdateLocationDto dto) {
        if (CollectionUtils.isEmpty(dto.getItemNumbers())) {
            throw new IllegalArgumentException("[itemNumbers] should not be empty");
        }
		List<Item> items = ItemUtils.filterByFieldsCritaria(this.findAllByItemNumbers(dto.getItemNumbers()),
                dto.getItemFieldsCriteria());
		if (CollectionUtils.isEmpty(items)) {
		    return;
        }
        items.forEach(item -> {
			item.setIpps(dto.isIpps());
			item.setSb(dto.isSb());
		});
		itemDAO.saveAll(items);
    }

    @Transactional
    public void save(Item item) {
        FieldSet fieldSet = new FieldSet();
        item.setFieldSet(fieldSet);
        fieldSetService.save(item.getFieldSet());
        if (!CollectionUtils.isEmpty(item.getFields())) {
            item.getFields().forEach(field -> field.setFieldSet(fieldSet));
            fieldService.saveAll(item.getFields());
        }
        itemDAO.save(item);
        itemFieldConfigService.saveAll(item.getItemFieldConfigs());
    }

    @Transactional
    public void saveAll(List<Item> items) {
        List<ItemFieldConfig> itemFieldConfigs = new ArrayList<>();
        List<FieldSet> fieldSets = new ArrayList<>();
        List<Field> fields = new ArrayList<>();
        for (Item item : items) {
            FieldSet fieldSet = new FieldSet();
            item.setFieldSet(fieldSet);
            fieldSets.add(fieldSet);
            if (!CollectionUtils.isEmpty(item.getFields())) {
                item.getFields().forEach(field -> {
                    field.setFieldSet(fieldSet);
                    fields.add(field);
                });
            }
            if (!CollectionUtils.isEmpty(item.getItemFieldConfigs())) {
                itemFieldConfigs.addAll(item.getItemFieldConfigs());
            }
        }
        fieldSetService.saveAll(fieldSets);
        fieldService.saveAll(fields);
        itemDAO.saveAll(items);
        itemFieldConfigService.saveAll(itemFieldConfigs);
    }

    public List<String> getAllItemNumbers() {
        return fieldService.findByFieldConfigName(AppFields.D2COMM_ITEM_NUMBER);
    }

    public List<Item> findByItemNumber(String itemNumber) {
        return itemDAO.findAllByField(AppFields.D2COMM_ITEM_NUMBER, itemNumber);
    }

    public List<Item> findAllByItemNumbers(List<String> itemNumbers) {
        return itemDAO.findAllByFieldsLight(AppFields.D2COMM_ITEM_NUMBER, itemNumbers);
    }

    public List<Item> getAll() {
        return (List<Item>) itemDAO.findAll();
    }

    public Optional<Item> getById(Long id) {
        return itemDAO.findById(id);
    }

    @Transactional
    public void delete(Item item) {
        fieldService.deleteByFieldSet(item.getFieldSet());
        itemDAO.delete(item);
    }

    @Transactional
    public void deleteByItemNumber(String itemNumber) {
        List<Item> items = findByItemNumber(itemNumber);
        List<FieldSet> fieldSets = items.stream().map(Item::getFieldSet).collect(Collectors.toList());
        fieldService.deleteByFieldSets(fieldSets);
        this.itemDAO.deleteAll(items);
    }
}
