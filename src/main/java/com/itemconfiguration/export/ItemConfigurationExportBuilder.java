package com.itemconfiguration.export;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.dto.ExportDataDto;

import java.util.List;

public interface ItemConfigurationExportBuilder {
    String build(ExportDataDto exportData);
}
