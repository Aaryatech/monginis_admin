package com.ats.adminpanel.model.stock;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
 

public class StoreStockDetail {

	
private int storeStockDetailId;
	 
	private int storeStockId;
	 
	private String storeStockDate;
	 
	private float storeOpeningStock;
	
	private float storeClosingStock;
	 
	private int rmId;
	 
	private String rmName;
	 
	private int rmGroup;
	 
	private int rmUom;
	
	 
	private float purRecQty;
	 
	private float bmsIssueQty;
	
	  
	private int isDelete;
	 
	private int exInt1;
	 
	private int exInt2;
	 
	private int exBool;

	public int getStoreStockDetailId() {
		return storeStockDetailId;
	}

	public void setStoreStockDetailId(int storeStockDetailId) {
		this.storeStockDetailId = storeStockDetailId;
	}

	public int getStoreStockId() {
		return storeStockId;
	}

	public void setStoreStockId(int storeStockId) {
		this.storeStockId = storeStockId;
	}

 
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getStoreStockDate() {
		return storeStockDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public void setStoreStockDate(String storeStockDate) {
		this.storeStockDate = storeStockDate;
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

	public int getRmGroup() {
		return rmGroup;
	}

	public void setRmGroup(int rmGroup) {
		this.rmGroup = rmGroup;
	}

	public int getRmUom() {
		return rmUom;
	}

	public void setRmUom(int rmUom) {
		this.rmUom = rmUom;
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

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public int getExBool() {
		return exBool;
	}

	public void setExBool(int exBool) {
		this.exBool = exBool;
	}

	@Override
	public String toString() {
		return "StoreStockDetail [storeStockDetailId=" + storeStockDetailId + ", storeStockId=" + storeStockId
				+ ", storeStockDate=" + storeStockDate + ", storeOpeningStock=" + storeOpeningStock
				+ ", storeClosingStock=" + storeClosingStock + ", rmId=" + rmId + ", rmName=" + rmName + ", rmGroup="
				+ rmGroup + ", rmUom=" + rmUom + ", purRecQty=" + purRecQty + ", bmsIssueQty=" + bmsIssueQty
				+ ", isDelete=" + isDelete + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exBool=" + exBool + "]";
	}

	 
	
	
}
