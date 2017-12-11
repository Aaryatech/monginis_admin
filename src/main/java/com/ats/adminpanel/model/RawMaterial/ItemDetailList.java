package com.ats.adminpanel.model.RawMaterial;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;


public class ItemDetailList {

	List<ItemDetail> itemDetailList;
	ErrorMessage errorMessage;
	
	public List<ItemDetail> getItemDetailList() {
		return itemDetailList;
	}
	public void setItemDetailList(List<ItemDetail> itemDetailList) {
		this.itemDetailList = itemDetailList;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "ItemDetailList [itemDetailList=" + itemDetailList + ", errorMessage=" + errorMessage + "]";
	}
	
	
}
