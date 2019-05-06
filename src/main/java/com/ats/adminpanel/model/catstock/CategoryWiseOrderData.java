package com.ats.adminpanel.model.catstock;

public class CategoryWiseOrderData {

	private int billDetailNo;
	
	private int subCatId;
	
	private int frId;
	
	private String frName;
	
	private int itemId;
	
	private String itemName;
	
	private float orderQty;
	
	private float billQty;
	
	private float grnQty;

	public int getBillDetailNo() {
		return billDetailNo;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public int getFrId() {
		return frId;
	}

	public String getFrName() {
		return frName;
	}

	public int getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public float getOrderQty() {
		return orderQty;
	}

	public float getBillQty() {
		return billQty;
	}

	public float getGrnQty() {
		return grnQty;
	}

	public void setBillDetailNo(int billDetailNo) {
		this.billDetailNo = billDetailNo;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setOrderQty(float orderQty) {
		this.orderQty = orderQty;
	}

	public void setBillQty(float billQty) {
		this.billQty = billQty;
	}

	public void setGrnQty(float grnQty) {
		this.grnQty = grnQty;
	}

	@Override
	public String toString() {
		return "CategoryWiseOrderData [billDetailNo=" + billDetailNo + ", subCatId=" + subCatId + ", frId=" + frId
				+ ", frName=" + frName + ", itemId=" + itemId + ", itemName=" + itemName + ", orderQty=" + orderQty
				+ ", billQty=" + billQty + ", grnQty=" + grnQty + "]";
	}

	
	
}
