package com.kordunyan.service.savestrategy;

import com.kordunyan.dto.SaveItemFieldConfigDto;
import org.springframework.stereotype.Component;

@Component("onlynew")
public class OnlyNewItemFieldConfigSaveStrategy implements ItemFieldConfigSaveStrategy {
	@Override
	public void save(SaveItemFieldConfigDto saveItemFieldConfigDto) {
		System.out.println("Only new");
	}
}
