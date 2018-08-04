package com.itemconfiguration.controller;


import com.itemconfiguration.domain.AppFields;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;
import com.itemconfiguration.dto.ExportDataDto;
import com.itemconfiguration.export.AllItemsConfigurationExportBuilder;
import com.itemconfiguration.export.ItemConfigurationExportBuilder;
import com.itemconfiguration.service.FieldConfigService;
import com.itemconfiguration.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportController {

    private ItemService itemService;
    private ItemConfigurationExportBuilder itemConfigurationExportBuilder;
    private FieldConfigService fieldConfigService;

    public ExportController(ItemService itemService, ItemConfigurationExportBuilder itemConfigurationExportBuilder,
            FieldConfigService fieldConfigService) {
        this.itemService = itemService;
        this.itemConfigurationExportBuilder = itemConfigurationExportBuilder;
        this.fieldConfigService = fieldConfigService;
    }

    @GetMapping("/generate")
    public String generateExport() {
        List<Item> items = itemService.getAll();
        FieldConfigsWrapper fieldConfigsWrapper = new FieldConfigsWrapper(fieldConfigService.getByOwner(AppFields.OWNER_ITEM));
        ExportDataDto data = new ExportDataDto(items, fieldConfigsWrapper);
        return itemConfigurationExportBuilder.build(data);
    }

}
