package com.ats.adminpanel.model.stock;

public class GetCurProdAndBillQty {
	
	
	int id;
	
	String itemName;
	
	int prodQty;
	int rejectedQty;
	int billQty;
	int damagedQty;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getProdQty() {
		return prodQty;
	}
	public void setProdQty(int prodQty) {
		this.prodQty = prodQty;
	}
	public int getRejectedQty() {
		return rejectedQty;
	}
	public void setRejectedQty(int rejectedQty) {
		this.rejectedQty = rejectedQty;
	}
	public int getBillQty() {
		return billQty;
	}
	public void setBillQty(int billQty) {
		this.billQty = billQty;
	}
	public int getDamagedQty() {
		return damagedQty;
	}
	public void setDamagedQty(int damagedQty) {
		this.damagedQty = damagedQty;
	}
	@Override
	public String toString() {
		return "GetCurProdAndBillQty [id=" + id + ", itemName=" + itemName + ", prodQty=" + prodQty + ", rejectedQty="
				+ rejectedQty + ", billQty=" + billQty + ", damagedQty=" + damagedQty + "]";
	}
	
	

}
