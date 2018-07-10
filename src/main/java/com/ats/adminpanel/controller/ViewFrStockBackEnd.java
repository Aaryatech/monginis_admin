package com.ats.adminpanel.controller;

public class ViewFrStockBackEnd {
	
	int frId;
	
	int itemId;
	
	String itemName;

	int regCurStock;
	

	
	
	public int getFrId() {
		return frId;
	}

	public int getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public int getRegCurStock() {
		return regCurStock;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setRegCurStock(int regCurStock) {
		this.regCurStock = regCurStock;
	}

	@Override
	public String toString() {
		return "ViewFrStockBackEnd [frId=" + frId + ", itemId=" + itemId + ", itemName=" + itemName + ", regCurStock="
				+ regCurStock + "]";
	}
	
	
}
