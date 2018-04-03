package com.ats.adminpanel.model;



public class DispatchReport {
	

	private int billDetailNo;
	
	private int billNo;
	
	private int catId;
	
	private int subCatId;
	
	private String catName;
	
	private int frId;
	
	private String frName;
	
	private int itemId;
	
	private String itemName;
	
	private int orderQty;
	
	private int billQty;

	
	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getBillDetailNo() {
		return billDetailNo;
	}

	public void setBillDetailNo(int billDetailNo) {
		this.billDetailNo = billDetailNo;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public int getBillQty() {
		return billQty;
	}

	public void setBillQty(int billQty) {
		this.billQty = billQty;
	}

	@Override
	public String toString() {
		return "DispatchReport [billDetailNo=" + billDetailNo + ", billNo=" + billNo + ", catId=" + catId
				+ ", subCatId=" + subCatId + ", catName=" + catName + ", frId=" + frId + ", frName=" + frName
				+ ", itemId=" + itemId + ", itemName=" + itemName + ", orderQty=" + orderQty + ", billQty=" + billQty
				+ "]";
	}

    
}
