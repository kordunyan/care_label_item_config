package com.itemconfiguration.dao;

import com.itemconfiguration.domain.DataType;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface DataTypeRepository extends CrudRepository<DataType, String>{

    List<DataType> findAllByNameIn(List<String> names);

}
