package com.ats.adminpanel.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;



public class BmsStockHeader {
	
	
	
	private int bmsStockId;
	private Date bmsStockDate;
	private int bmsStatus;
	private int rmType;
	private int exInt;
	private int exInt1;
	private int exBoll;
	private int exBoll1;
	private String exVarchar;
	List<BmsStockDetailed> bmsStockDetailed;
	public int getBmsStockId() {
		return bmsStockId;
	}
	public void setBmsStockId(int bmsStockId) {
		this.bmsStockId = bmsStockId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getBmsStockDate() {
		return bmsStockDate;
	}
	public void setBmsStockDate(Date bmsStockDate) {
		this.bmsStockDate = bmsStockDate;
	}
	public int getBmsStatus() {
		return bmsStatus;
	}
	public void setBmsStatus(int bmsStatus) {
		this.bmsStatus = bmsStatus;
	}
	public int getRmType() {
		return rmType;
	}
	public void setRmType(int rmType) {
		this.rmType = rmType;
	}
	public int getExInt() {
		return exInt;
	}
	public void setExInt(int exInt) {
		this.exInt = exInt;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public int getExBoll() {
		return exBoll;
	}
	public void setExBoll(int exBoll) {
		this.exBoll = exBoll;
	}
	public int getExBoll1() {
		return exBoll1;
	}
	public void setExBoll1(int exBoll1) {
		this.exBoll1 = exBoll1;
	}
	public String getExVarchar() {
		return exVarchar;
	}
	public void setExVarchar(String exVarchar) {
		this.exVarchar = exVarchar;
	}
	public List<BmsStockDetailed> getBmsStockDetailed() {
		return bmsStockDetailed;
	}
	public void setBmsStockDetailed(List<BmsStockDetailed> bmsStockDetailed) {
		this.bmsStockDetailed = bmsStockDetailed;
	}
	@Override
	public String toString() {
		return "BmsStockHeader [bmsStockId=" + bmsStockId + ", bmsStockDate=" + bmsStockDate + ", bmsStatus="
				+ bmsStatus + ", rmType=" + rmType + ", exInt=" + exInt + ", exInt1=" + exInt1 + ", exBoll=" + exBoll
				+ ", exBoll1=" + exBoll1 + ", exVarchar=" + exVarchar + ", bmsStockDetailed=" + bmsStockDetailed + "]";
	}
	
	
	

}
