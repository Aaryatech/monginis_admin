package com.ats.adminpanel.model.item;

public class FrItemStock {
	
	
	int frStockId;
	int itemId;
	int type;
	int minQty;
	int maxQty;
	int reorderQty;
	
	
	
	public int getReorderQty() {
		return reorderQty;
	}




	public void setReorderQty(int reorderQty) {
		this.reorderQty = reorderQty;
	}




	public int getFrStockId() {
		return frStockId;
	}




	public void setFrStockId(int frStockId) {
		this.frStockId = frStockId;
	}




	public int getItemId() {
		return itemId;
	}




	public void setItemId(int itemId) {
		this.itemId = itemId;
	}




	public int getType() {
		return type;
	}




	public void setType(int type) {
		this.type = type;
	}




	public int getMinQty() {
		return minQty;
	}




	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}




	public int getMaxQty() {
		return maxQty;
	}




	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}




	@Override
	public String toString() {
		return "FrItemStock [frStockId=" + frStockId + ", itemId=" + itemId + ", type=" + type + ", minQty=" + minQty
				+ ", maxQty=" + maxQty + ", reorderQty=" + reorderQty + "]";
	}




	



	
}
