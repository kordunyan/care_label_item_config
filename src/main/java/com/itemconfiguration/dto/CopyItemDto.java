package com.itemconfiguration.dto;

import com.itemconfiguration.domain.Item;
import com.itemconfiguration.service.savestrategy.item.strategy.CopyFieldsStrategy;

import java.util.List;

public class CopyItemDto {

	private List<Item> items;
	private String copyItemNumber;
	private boolean withItemFieldConfigs;
	private boolean withMandatoryData;
	private long copyItemId;
	private CopyFieldsStrategy copyFieldsStrategy;

	public CopyItemDto() {
	}

	public long getCopyItemId() {
		return copyItemId;
	}

	public void setCopyItemId(long copyItemId) {
		this.copyItemId = copyItemId;
	}

	public List<Item> getItems() {
		return items;
	}

	public CopyFieldsStrategy getCopyFieldsStrategy() {
		return copyFieldsStrategy;
	}

	public void setCopyFieldsStrategy(CopyFieldsStrategy copyFieldsStrategy) {
		this.copyFieldsStrategy = copyFieldsStrategy;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getCopyItemNumber() {
		return copyItemNumber;
	}

	public void setCopyItemNumber(String copyItemNumber) {
		this.copyItemNumber = copyItemNumber;
	}

	public boolean isWithItemFieldConfigs() {
		return withItemFieldConfigs;
	}

	public void setWithItemFieldConfigs(boolean withItemFieldConfigs) {
		this.withItemFieldConfigs = withItemFieldConfigs;
	}

	public boolean isWithMandatoryData() {
		return withMandatoryData;
	}

	public void setWithMandatoryData(boolean withMandatoryData) {
		this.withMandatoryData = withMandatoryData;
	}
}
