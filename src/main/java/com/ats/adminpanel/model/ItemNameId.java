package com.ats.adminpanel.model;

public class ItemNameId {
	String id;
	String itemName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Override
	public String toString() {
		
		return "ItemNameId [id=" + id + ", itemName=" + itemName + "]";
	}
	

}
