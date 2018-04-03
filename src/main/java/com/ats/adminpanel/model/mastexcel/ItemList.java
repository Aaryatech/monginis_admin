package com.ats.adminpanel.model.mastexcel;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;


public class ItemList {
	 
	
	List<TallyItem> itemList;
	ErrorMessage errorMessage;
	public List<TallyItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<TallyItem> itemList) {
		this.itemList = itemList;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "ItemList [itemList=" + itemList + ", errorMessage=" + errorMessage + "]";
	}

	
}
