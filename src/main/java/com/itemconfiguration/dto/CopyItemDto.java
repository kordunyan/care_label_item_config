package com.itemconfiguration.dto;

import com.itemconfiguration.domain.Item;

import java.util.List;

public class CopyItemDto {
	private List<Item> items;
	private Long copyItemId;

	public CopyItemDto() {
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Long getCopyItemId() {
		return copyItemId;
	}

	public void setCopyItemId(Long copyItemId) {
		this.copyItemId = copyItemId;
	}
}
