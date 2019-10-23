package com.ats.adminpanel.model;

import java.util.List;
 

public class ItemListWithDateRecord {
	
	private int itemId;
	private String itemName;  
	List<DateAndQty> list;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public List<DateAndQty> getList() {
		return list;
	}
	public void setList(List<DateAndQty> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "ItemListWithDateRecord [itemId=" + itemId + ", itemName=" + itemName + ", list=" + list + "]";
	}
	
	

}
