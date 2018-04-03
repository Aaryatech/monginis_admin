package com.ats.adminpanel.model;

public class BmsStockItemList {

	private int bsmId;
	private int itemId;
	private String name;
	private String uomName;
	private int uomId;
	private float qty;
	public int getBsmId() {
		return bsmId;
	}
	public void setBsmId(int bsmId) {
		this.bsmId = bsmId;
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
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "BmsStockItemList [bsmid=" + bsmId + ", itemId=" + itemId + ", name=" + name + ", uomName=" + uomName
				+ ", uomId=" + uomId + ", qty=" + qty + "]";
	}
	
	
}
