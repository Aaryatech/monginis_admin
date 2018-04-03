package com.ats.adminpanel.model.stock;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

 

public class StoreStockHeader {

	
private boolean isError;
	
	 
	private String message;
	 
	private int storeStockId;
	 
	private String storeStockDate;
	 
	private int storeStockStatus; 
	
	private int exInt1;
	 
	private int exInt2;
	 
	private int exBoll1;
	 
	private int exBoll2;
	
	List<StoreStockDetail> storeStockDetailList;

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStoreStockId() {
		return storeStockId;
	}

	public void setStoreStockId(int storeStockId) {
		this.storeStockId = storeStockId;
	}
	/*@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")*/
	 

	public int getStoreStockStatus() {
		return storeStockStatus;
	}

	public String getStoreStockDate() {
		return storeStockDate;
	}

	public void setStoreStockDate(String storeStockDate) {
		this.storeStockDate = storeStockDate;
	}

	public void setStoreStockStatus(int storeStockStatus) {
		this.storeStockStatus = storeStockStatus;
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

	public int getExBoll1() {
		return exBoll1;
	}

	public void setExBoll1(int exBoll1) {
		this.exBoll1 = exBoll1;
	}

	public int getExBoll2() {
		return exBoll2;
	}

	public void setExBoll2(int exBoll2) {
		this.exBoll2 = exBoll2;
	}

	public List<StoreStockDetail> getStoreStockDetailList() {
		return storeStockDetailList;
	}

	public void setStoreStockDetailList(List<StoreStockDetail> storeStockDetailList) {
		this.storeStockDetailList = storeStockDetailList;
	}

	@Override
	public String toString() {
		return "StoreStockHeader [isError=" + isError + ", message=" + message + ", storeStockId=" + storeStockId
				+ ", storeStockDate=" + storeStockDate + ", storeStockStatus=" + storeStockStatus + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exBoll1=" + exBoll1 + ", exBoll2=" + exBoll2 + ", storeStockDetailList="
				+ storeStockDetailList + "]";
	}

	 
	
}
