package com.itemconfiguration.controller;


import com.itemconfiguration.domain.AppFields;
import com.itemconfiguration.domain.Item;
import com.itemconfiguration.domain.wrapper.FieldConfigsWrapper;
import com.itemconfiguration.dto.ExportDataDto;
import com.itemconfiguration.export.ItemConfigurationExportBuilder;
import com.itemconfiguration.service.FieldConfigService;
import com.itemconfiguration.service.ItemService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        FieldConfigsWrapper fieldConfigsWrapper = new FieldConfigsWrapper(fieldConfigService.getByOwner(AppFields.OWNER_ITEM));
        ExportDataDto data = new ExportDataDto(items, fieldConfigsWrapper);
        ByteArrayResource resource = new ByteArrayResource(itemConfigurationExportBuilder.build(data).getBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=item_field_config.sql")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }

    @GetMapping("/generateby")
    public String generateExportBy() {


//        //All EU
        List<String> itemNumbers = Arrays.asList("032463-W"
        );


        //        //All Costco
//        List<String> itemNumbers = Arrays.asList(
//                "PR-100010","PR-100011"
//        );




                List<Item> items = itemService.findAllByItemNumbers(itemNumbers);
        FieldConfigsWrapper fieldConfigsWrapper = new FieldConfigsWrapper(fieldConfigService.getByOwner(AppFields.OWNER_ITEM));
        ExportDataDto data = new ExportDataDto(items, fieldConfigsWrapper);
        return itemConfigurationExportBuilder.build(data);
    }

}
