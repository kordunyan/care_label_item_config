package com.itemconfiguration.export.bilder.block.datatype;

import com.itemconfiguration.domain.DataType;
import com.itemconfiguration.export.bilder.line.DataTypeLineBuilder;
import com.itemconfiguration.export.bilder.line.StaticLines;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DataTypeBlockBuilder {
    private final DataTypeLineBuilder dataTypeLineBuilder;

    public DataTypeBlockBuilder(DataTypeLineBuilder dataTypeLineBuilder) {
        this.dataTypeLineBuilder = dataTypeLineBuilder;
    }

    public String build(List<DataType> dataTypes) {
        if (CollectionUtils.isEmpty(dataTypes)) {
            return StringUtils.EMPTY;
        }
        return dataTypes.stream()
                .map(dataTypeLineBuilder::build)
                .collect(Collectors.joining(StaticLines.INSERT_VALUES_SEPARATOR));
    }
}
