package com.itemconfiguration.controller;

import com.itemconfiguration.domain.ItemFieldConfig;
import com.itemconfiguration.dto.DeleteMandatoryDataDto;
import com.itemconfiguration.dto.SaveMandatoryDataDto;
import com.itemconfiguration.service.deletestrategy.mandatory.MandatoryDataDeleteStrategyProvider;
import com.itemconfiguration.service.savestrategy.mandatory.MandatoryDataSaveStrategyProvider;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/mandatory/data")
public class MandatoryDataController {

	private final MandatoryDataSaveStrategyProvider saveStrategyProvider;
	private final MandatoryDataDeleteStrategyProvider deleteStrategyProvider;

	public MandatoryDataController(MandatoryDataSaveStrategyProvider saveStrategyProvider,
			MandatoryDataDeleteStrategyProvider deleteStrategyProvider) {
		this.saveStrategyProvider = saveStrategyProvider;
		this.deleteStrategyProvider = deleteStrategyProvider;
	}

	@PostMapping("/save")
	public List<ItemFieldConfig> saveNew(@RequestBody() SaveMandatoryDataDto saveMandatoryDataDto) {
		saveStrategyProvider.getSaveStrategy(saveMandatoryDataDto).save(saveMandatoryDataDto);
		return saveMandatoryDataDto.getItemFieldConfigs();
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteTranslations(@RequestBody DeleteMandatoryDataDto dto) {
		deleteStrategyProvider.getDeleteFieldsStrategy(dto).delete(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
