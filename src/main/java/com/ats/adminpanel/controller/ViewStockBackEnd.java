package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.List;

import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.FrIdNames;
import com.ats.adminpanel.model.ItemNameId;
import com.ats.adminpanel.model.item.Item;

public class ViewStockBackEnd {
	
	
	List<ViewFrStockBackEnd> currentStockDetailList;
	
	List<ItemNameId> itemList;
	
	List<FrIdNames> frIdNamesList;

	public List<ViewFrStockBackEnd> getCurrentStockDetailList() {
		return currentStockDetailList;
	}

	public List<ItemNameId> getItemList() {
		return itemList;
	}

	
	public void setCurrentStockDetailList(List<ViewFrStockBackEnd> currentStockDetailList) {
		this.currentStockDetailList = currentStockDetailList;
	}

	public void setItemList(List<ItemNameId> itemList) {
		this.itemList = itemList;
	}

	public List<FrIdNames> getFrIdNamesList() {
		return frIdNamesList;
	}

	public void setFrIdNamesList(List<FrIdNames> frIdNamesList) {
		this.frIdNamesList = frIdNamesList;
	}

	@Override
	public String toString() {
		return "ViewStockBackEnd [currentStockDetailList=" + currentStockDetailList + ", itemList=" + itemList
				+ ", frIdNamesList=" + frIdNamesList + "]";
	}

}
