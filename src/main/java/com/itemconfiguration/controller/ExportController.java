package com.itemconfiguration.controller;


import com.itemconfiguration.AppProperties;
import com.itemconfiguration.domain.AppFields;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;
import com.itemconfiguration.dto.ExportDataDto;
import com.itemconfiguration.dto.ExportFieldConfigsDto;
import com.itemconfiguration.export.FieldConfigsExportBuilder;
import com.itemconfiguration.export.ItemConfigurationExportBuilder;
import com.itemconfiguration.service.FieldConfigService;
import com.itemconfiguration.service.ItemService;
import com.itemconfiguration.utils.WebUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    @Value(AppProperties.KEY_ITEM_FIELD_CONFIG_FILE_NAME)
    private String EXPORT_FILE_NAME;
    private ItemService itemService;
    private ItemConfigurationExportBuilder itemConfigurationExportBuilder;
    private FieldConfigService fieldConfigService;
    private FieldConfigsExportBuilder fieldConfigsExportBuilder;

    public ExportController(ItemService itemService, ItemConfigurationExportBuilder itemConfigurationExportBuilder,
            FieldConfigService fieldConfigService, FieldConfigsExportBuilder fieldConfigsExportBuilder) {
        this.itemService = itemService;
        this.itemConfigurationExportBuilder = itemConfigurationExportBuilder;
        this.fieldConfigService = fieldConfigService;
        this.fieldConfigsExportBuilder = fieldConfigsExportBuilder;
    }

    @GetMapping("/generate")
    public ResponseEntity<ByteArrayResource> generateExport() {
        List<Item> items = itemService.getAll();
        List<String> itemNumbers = itemService.getAllItemNumbers();
        FieldConfigsWrapper fieldConfigsWrapper = fieldConfigService.getByOwnerFieldConfigWraper(AppFields.OWNER_ITEM);
        ExportDataDto data = new ExportDataDto(items, fieldConfigsWrapper, itemNumbers);
        return WebUtils.stringToDownloadFile(itemConfigurationExportBuilder.build(data), EXPORT_FILE_NAME);
    }

    @PostMapping("/generateby")
    public  ResponseEntity<ByteArrayResource> generateExportBy(@RequestBody List<String> itemNumbers) {
        List<Item> items = itemService.findAllByItemNumbers(itemNumbers);
        FieldConfigsWrapper fieldConfigsWrapper = fieldConfigService.getByOwnerFieldConfigWraper(AppFields.OWNER_ITEM);
        ExportDataDto data = new ExportDataDto(items, fieldConfigsWrapper, itemNumbers);
        return WebUtils.stringToDownloadFile(itemConfigurationExportBuilder.build(data), EXPORT_FILE_NAME);
    }

    @PostMapping("/field-configs")
    public String exportFieldConfigs(@RequestBody ExportFieldConfigsDto dto) {
        fieldConfigsExportBuilder.export(dto);
        return "";
    }

}
