package com.ats.adminpanel.model.production;

import java.util.List;

import com.ats.adminpanel.model.item.Item;

public class PlanQtyAjaxResponse {

	List<GetProductionItemQty> getProductionItemQtyList;
	
	List<Item> itemList;

	public List<GetProductionItemQty> getGetProductionItemQtyList() {
		return getProductionItemQtyList;
	}

	public void setGetProductionItemQtyList(List<GetProductionItemQty> getProductionItemQtyList) {
		this.getProductionItemQtyList = getProductionItemQtyList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "PlanQtyAjaxResponse [getProductionItemQtyList=" + getProductionItemQtyList + ", itemList=" + itemList
				+ "]";
	}
	
	
}
