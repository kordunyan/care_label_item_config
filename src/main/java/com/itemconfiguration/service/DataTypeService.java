package com.itemconfiguration.service;

import com.itemconfiguration.dao.DataTypeRepository;
import com.itemconfiguration.domain.DataType;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DataTypeService {

    private final DataTypeRepository dataTypeRepository;

    public DataTypeService(DataTypeRepository dataTypeRepository) {
        this.dataTypeRepository = dataTypeRepository;
    }

    public List<DataType> getByNames(List<String> names) {
        return dataTypeRepository.findAllByNameIn(names);
    }
}
