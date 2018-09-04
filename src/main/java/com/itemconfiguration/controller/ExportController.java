package com.itemconfiguration.controller;


import com.itemconfiguration.AppProperties;
import com.itemconfiguration.domain.AppFields;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;
import com.itemconfiguration.dto.ExportDataDto;
import com.itemconfiguration.export.ItemConfigurationExportBuilder;
import com.itemconfiguration.service.FieldConfigService;
import com.itemconfiguration.service.ItemService;
import com.itemconfiguration.utils.WebUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Value(AppProperties.KEY_ITEM_FIELD_CONFIG_FILE_NAME)
    private String EXPORT_FILE_NAME;
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
    public ResponseEntity<ByteArrayResource> generateExport() {
        List<Item> items = itemService.getAll();
        List<String> itemNumbers = itemService.getAllItemNumbers();
        FieldConfigsWrapper fieldConfigsWrapper = new FieldConfigsWrapper(fieldConfigService.getByOwner(AppFields.OWNER_ITEM));
        ExportDataDto data = new ExportDataDto(items, fieldConfigsWrapper, itemNumbers);
        return WebUtils.stringToDownloadFile(itemConfigurationExportBuilder.build(data), EXPORT_FILE_NAME);
    }

    @PostMapping("/generateby")
    public  ResponseEntity<ByteArrayResource> generateExportBy(@RequestBody List<String> itemNumbers) {
        List<Item> items = itemService.findAllByItemNumbers(itemNumbers);
        FieldConfigsWrapper fieldConfigsWrapper = new FieldConfigsWrapper(fieldConfigService.getByOwner(AppFields.OWNER_ITEM));
        ExportDataDto data = new ExportDataDto(items, fieldConfigsWrapper, itemNumbers);
        return WebUtils.stringToDownloadFile(itemConfigurationExportBuilder.build(data), EXPORT_FILE_NAME);
    }

}
