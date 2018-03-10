package com.ats.adminpanel.model.production;

public class ProductionBarcode {

	private int itemId;
	private int productionQty;
	private String itemCode;
	private String itemName;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getProductionQty() {
		return productionQty;
	}
	public void setProductionQty(int productionQty) {
		this.productionQty = productionQty;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	@Override
	public String toString() {
		return "ProductionBarcode [itemId=" + itemId + ", productionQty=" + productionQty + ", itemCode=" + itemCode
				+ ", itemName=" + itemName + "]";
	}
	
	
	
	
	
}
