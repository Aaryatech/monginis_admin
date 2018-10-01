package com.ats.adminpanel.model.stock;


public class GetPoGenItemStock{

	private int storeStockDetailId;
	
	private float storeOpeningStock;
	
	private float storeClosingStock;
	
	private String storeStockDate;
	
	private int rmId;
	
	private String rmName;
	
	private String rmCode;
	
	private float rmMinQty;
	
	private float rmMaxQty;
	
	private float rmRolQty;
	
	private float purRecQty;

	private float bmsIssueQty;
	
	private int poNo;

	private int poQty;

	public int getStoreStockDetailId() {
		return storeStockDetailId;
	}

	public float getStoreOpeningStock() {
		return storeOpeningStock;
	}

	public float getStoreClosingStock() {
		return storeClosingStock;
	}
	public String getStoreStockDate() {
		return storeStockDate;
	}

	public int getRmId() {
		return rmId;
	}

	public String getRmName() {
		return rmName;
	}

	public String getRmCode() {
		return rmCode;
	}

	public float getRmMinQty() {
		return rmMinQty;
	}

	public float getRmMaxQty() {
		return rmMaxQty;
	}

	public float getRmRolQty() {
		return rmRolQty;
	}

	public float getPurRecQty() {
		return purRecQty;
	}

	public float getBmsIssueQty() {
		return bmsIssueQty;
	}

	public int getPoNo() {
		return poNo;
	}

	public int getPoQty() {
		return poQty;
	}

	public void setStoreStockDetailId(int storeStockDetailId) {
		this.storeStockDetailId = storeStockDetailId;
	}

	public void setStoreOpeningStock(float storeOpeningStock) {
		this.storeOpeningStock = storeOpeningStock;
	}

	public void setStoreClosingStock(float storeClosingStock) {
		this.storeClosingStock = storeClosingStock;
	}

	public void setStoreStockDate(String storeStockDate) {
		this.storeStockDate = storeStockDate;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public void setRmCode(String rmCode) {
		this.rmCode = rmCode;
	}

	public void setRmMinQty(float rmMinQty) {
		this.rmMinQty = rmMinQty;
	}

	public void setRmMaxQty(float rmMaxQty) {
		this.rmMaxQty = rmMaxQty;
	}

	public void setRmRolQty(float rmRolQty) {
		this.rmRolQty = rmRolQty;
	}

	public void setPurRecQty(float purRecQty) {
		this.purRecQty = purRecQty;
	}

	public void setBmsIssueQty(float bmsIssueQty) {
		this.bmsIssueQty = bmsIssueQty;
	}

	public void setPoNo(int poNo) {
		this.poNo = poNo;
	}

	public void setPoQty(int poQty) {
		this.poQty = poQty;
	}

	@Override
	public String toString() {
		return "GetPoGenItemStock [storeStockDetailId=" + storeStockDetailId + ", storeOpeningStock="
				+ storeOpeningStock + ", storeClosingStock=" + storeClosingStock + ", storeStockDate=" + storeStockDate
				+ ", rmId=" + rmId + ", rmName=" + rmName + ", rmCode=" + rmCode + ", rmMinQty=" + rmMinQty
				+ ", rmMaxQty=" + rmMaxQty + ", rmRolQty=" + rmRolQty + ", purRecQty=" + purRecQty + ", bmsIssueQty="
				+ bmsIssueQty + ", poNo=" + poNo + ", poQty=" + poQty + "]";
	}
	
	
	
	
}
