package com.ats.adminpanel.model;

public class SfndRawItem {
	
	private int itemId;
	private String name;
	private String uomName;
	private int uomId;
	private int Qty;
	
	
	
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public int getQty() {
		return Qty;
	}
	public void setQty(int qty) {
		Qty = qty;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	@Override
	public String toString() {
		return "SfndRawItem [itemId=" + itemId + ", name=" + name + ", uomName=" + uomName + ", uomId=" + uomId
				+ ", Qty=" + Qty + "]";
	}
	
	

}
