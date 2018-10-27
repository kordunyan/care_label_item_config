package com.itemconfiguration.dao;

import com.itemconfiguration.dao.resultobjects.InstructionLanguage;
import com.itemconfiguration.domain.InstructionTypeInputConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InstructionTypeInputConfigRepository extends CrudRepository<InstructionTypeInputConfig, Long>{

	@Query(value = "SELECT i.dataType AS instructionType, l.language AS language FROM InstructionTypeInputConfig i JOIN i.instructionTypeLanguageInputPositions l")
	List<InstructionLanguage> getIstructionLanguages();

}
