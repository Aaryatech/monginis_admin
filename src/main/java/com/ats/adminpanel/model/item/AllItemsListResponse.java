package com.ats.adminpanel.model.item;

import java.util.List;

import com.ats.adminpanel.model.modules.ErrorMessage;

public class AllItemsListResponse {


	List<Item> items;
	ErrorMessage errorMessage;
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}

