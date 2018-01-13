package com.ats.adminpanel.model.stock;

 

 

 
public class GetStoreCurrentStock {

	
 
	private int storeStockDetailId;
	 
	float storeOpeningStock;
 
	float storeClosingStock;
	 
	String storeStockDate;
	 
	private int rmId;
	 
	private String rmName;
	 
	private float purRecQty;
	
	 
	private float bmsIssueQty;


	public int getStoreStockDetailId() {
		return storeStockDetailId;
	}


	public void setStoreStockDetailId(int storeStockDetailId) {
		this.storeStockDetailId = storeStockDetailId;
	}


	public float getStoreOpeningStock() {
		return storeOpeningStock;
	}


	public void setStoreOpeningStock(float storeOpeningStock) {
		this.storeOpeningStock = storeOpeningStock;
	}


	public float getStoreClosingStock() {
		return storeClosingStock;
	}


	public void setStoreClosingStock(float storeClosingStock) {
		this.storeClosingStock = storeClosingStock;
	}


	public String getStoreStockDate() {
		return storeStockDate;
	}


	public void setStoreStockDate(String storeStockDate) {
		this.storeStockDate = storeStockDate;
	}


	public int getRmId() {
		return rmId;
	}


	public void setRmId(int rmId) {
		this.rmId = rmId;
	}


	public String getRmName() {
		return rmName;
	}


	public void setRmName(String rmName) {
		this.rmName = rmName;
	}


	public float getPurRecQty() {
		return purRecQty;
	}


	public void setPurRecQty(float purRecQty) {
		this.purRecQty = purRecQty;
	}


	public float getBmsIssueQty() {
		return bmsIssueQty;
	}


	public void setBmsIssueQty(float bmsIssueQty) {
		this.bmsIssueQty = bmsIssueQty;
	}


	@Override
	public String toString() {
		return "GetStoreCurrentStock [storeStockDetailId=" + storeStockDetailId + ", storeOpeningStock="
				+ storeOpeningStock + ", storeClosingStock=" + storeClosingStock + ", storeStockDate=" + storeStockDate
				+ ", rmId=" + rmId + ", rmName=" + rmName + ", purRecQty=" + purRecQty + ", bmsIssueQty=" + bmsIssueQty
				+ "]";
	}


	 


	 
	
	
}
