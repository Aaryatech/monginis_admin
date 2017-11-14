package com.ats.adminpanel.model.stock;

public class PostFrItemStockDetail {

	
	private int openingStockDetailId;
	
	private int openingStockHeaderId;

	private int 	itemId;
	
	private int openingStock;
	
	private int physicalStock;
	
	private int stockDifference;
	
	private int totalPurchase;
	
	private int totalGrnGvn;
	
	private int totalSell;
	
	private String remark;

	
	
	
	public int getOpeningStockDetailId() {
		return openingStockDetailId;
	}

	public void setOpeningStockDetailId(int openingStockDetailId) {
		this.openingStockDetailId = openingStockDetailId;
	}

	public int getOpeningStockHeaderId() {
		return openingStockHeaderId;
	}

	public void setOpeningStockHeaderId(int openingStockHeaderId) {
		this.openingStockHeaderId = openingStockHeaderId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getOpeningStock() {
		return openingStock;
	}

	public void setOpeningStock(int openingStock) {
		this.openingStock = openingStock;
	}

	public int getPhysicalStock() {
		return physicalStock;
	}

	public void setPhysicalStock(int physicalStock) {
		this.physicalStock = physicalStock;
	}

	public int getStockDifference() {
		return stockDifference;
	}

	public void setStockDifference(int stockDifference) {
		this.stockDifference = stockDifference;
	}

	public int getTotalPurchase() {
		return totalPurchase;
	}

	public void setTotalPurchase(int totalPurchase) {
		this.totalPurchase = totalPurchase;
	}

	public int getTotalGrnGvn() {
		return totalGrnGvn;
	}

	public void setTotalGrnGvn(int totalGrnGvn) {
		this.totalGrnGvn = totalGrnGvn;
	}

	public int getTotalSell() {
		return totalSell;
	}

	public void setTotalSell(int totalSell) {
		this.totalSell = totalSell;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
	@Override
	public String toString() {
		return "PostFrItemStockDetail [openingStockDetailId=" + openingStockDetailId + ", openingStockHeaderId="
				+ openingStockHeaderId + ", itemId=" + itemId + ", openingStock=" + openingStock + ", physicalStock="
				+ physicalStock + ", stockDifference=" + stockDifference + ", totalPurchase=" + totalPurchase
				+ ", totalGrnGvn=" + totalGrnGvn + ", totalSell=" + totalSell + ", remark=" + remark + "]";
	}
	
	
	

}
