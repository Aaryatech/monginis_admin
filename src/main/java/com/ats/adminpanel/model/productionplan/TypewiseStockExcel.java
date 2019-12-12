package com.ats.adminpanel.model.productionplan;

public class TypewiseStockExcel {

	private int itemLimit;
	private int type;
	private int frCount;

	public int getItemLimit() {
		return itemLimit;
	}

	public void setItemLimit(int itemLimit) {
		this.itemLimit = itemLimit;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

	public int getFrCount() {
		return frCount;
	}

	public void setFrCount(int frCount) {
		this.frCount = frCount;
	}
	
	

	@Override
	public String toString() {
		return "TypewiseStockExcel [itemLimit=" + itemLimit + ", type=" + type + ", frCount=" + frCount + "]";
	}

}
