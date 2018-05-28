package com.ats.adminpanel.model;

import java.util.List;

import com.ats.adminpanel.model.item.Item;

public class PlanDetails {

	List<Item> itemList;
	
	List<GetSubCategory> catList;

	public List<Item> getItemList() {
		return itemList;
	}

	public List<GetSubCategory> getCatList() {
		return catList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public void setCatList(List<GetSubCategory> catList) {
		this.catList = catList;
	}

	@Override
	public String toString() {
		return "PlanDetails [itemList=" + itemList + ", catList=" + catList + "]";
	}
	
	
}
