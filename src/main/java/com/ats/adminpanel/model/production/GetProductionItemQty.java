package com.ats.adminpanel.model.production;


public class GetProductionItemQty {

	private int productionDetailId;
	
	private int productionHeaderId;

	private int qty;
	
	private int itemId;

	private String productionDate;
	
	private String itemName;

	public int getProductionDetailId() {
		return productionDetailId;
	}

	public void setProductionDetailId(int productionDetailId) {
		this.productionDetailId = productionDetailId;
	}

	public int getProductionHeaderId() {
		return productionHeaderId;
	}

	public void setProductionHeaderId(int productionHeaderId) {
		this.productionHeaderId = productionHeaderId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "GetProductionItemQty [productionDetailId=" + productionDetailId + ", productionHeaderId="
				+ productionHeaderId + ", qty=" + qty + ", itemId=" + itemId + ", productionDate=" + productionDate
				+ ", itemName=" + itemName + "]";
	}
	
	
	
}
