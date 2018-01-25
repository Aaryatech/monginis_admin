package com.ats.adminpanel.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class BmsStockDetailed {

	
	private int bmsStockDeatilId;
	private int bmsStockId;
	private Date bmsStockDate;
	private int rmType;
	private int rmId;
	private String rmName;
	private int rmUom;
	private float bmsOpeningStock;
	private float storeRecQty;
	private float storeRejectedQty;
	private float mixingRecQty;
	private float mixingReceiveRejectedQty;
	private float mixingIssueQty;
	private float mixingReturnQty;
	private float mixingRejected;
	private float prodIssueQty;
	private float prodReturnQty;
	private float prodRejectedQty;
	private float closingQty;
	private int isDelStatus;
	private String uom;
	
	
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public int getBmsStockDeatilId() {
		return bmsStockDeatilId;
	}
	public void setBmsStockDeatilId(int bmsStockDeatilId) {
		this.bmsStockDeatilId = bmsStockDeatilId;
	}
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
	public int getRmType() {
		return rmType;
	}
	public void setRmType(int rmType) {
		this.rmType = rmType;
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
	public int getRmUom() {
		return rmUom;
	}
	public void setRmUom(int rmUom) {
		this.rmUom = rmUom;
	}
	public float getBmsOpeningStock() {
		return bmsOpeningStock;
	}
	public void setBmsOpeningStock(float bmsOpeningStock) {
		this.bmsOpeningStock = bmsOpeningStock;
	}
	public float getStoreRecQty() {
		return storeRecQty;
	}
	public void setStoreRecQty(float storeRecQty) {
		this.storeRecQty = storeRecQty;
	}
	public float getStoreRejectedQty() {
		return storeRejectedQty;
	}
	public void setStoreRejectedQty(float storeRejectedQty) {
		this.storeRejectedQty = storeRejectedQty;
	}
	public float getMixingRecQty() {
		return mixingRecQty;
	}
	public void setMixingRecQty(float mixingRecQty) {
		this.mixingRecQty = mixingRecQty;
	}
	public float getMixingReceiveRejectedQty() {
		return mixingReceiveRejectedQty;
	}
	public void setMixingReceiveRejectedQty(float mixingReceiveRejectedQty) {
		this.mixingReceiveRejectedQty = mixingReceiveRejectedQty;
	}
	public float getMixingIssueQty() {
		return mixingIssueQty;
	}
	public void setMixingIssueQty(float mixingIssueQty) {
		this.mixingIssueQty = mixingIssueQty;
	}
	public float getMixingReturnQty() {
		return mixingReturnQty;
	}
	public void setMixingReturnQty(float mixingReturnQty) {
		this.mixingReturnQty = mixingReturnQty;
	}
	public float getMixingRejected() {
		return mixingRejected;
	}
	public void setMixingRejected(float mixingRejected) {
		this.mixingRejected = mixingRejected;
	}
	public float getProdIssueQty() {
		return prodIssueQty;
	}
	public void setProdIssueQty(float prodIssueQty) {
		this.prodIssueQty = prodIssueQty;
	}
	public float getProdReturnQty() {
		return prodReturnQty;
	}
	public void setProdReturnQty(float prodReturnQty) {
		this.prodReturnQty = prodReturnQty;
	}
	public float getProdRejectedQty() {
		return prodRejectedQty;
	}
	public void setProdRejectedQty(float prodRejectedQty) {
		this.prodRejectedQty = prodRejectedQty;
	}
	public float getClosingQty() {
		return closingQty;
	}
	public void setClosingQty(float closingQty) {
		this.closingQty = closingQty;
	}
	public int getIsDelStatus() {
		return isDelStatus;
	}
	public void setIsDelStatus(int isDelStatus) {
		this.isDelStatus = isDelStatus;
	}
	@Override
	public String toString() {
		return "BmsStockDetailed [bmsStockDeatilId=" + bmsStockDeatilId + ", bmsStockId=" + bmsStockId
				+ ", bmsStockDate=" + bmsStockDate + ", rmType=" + rmType + ", rmId=" + rmId + ", rmName=" + rmName
				+ ", rmUom=" + rmUom + ", bmsOpeningStock=" + bmsOpeningStock + ", storeRecQty=" + storeRecQty
				+ ", storeRejectedQty=" + storeRejectedQty + ", mixingRecQty=" + mixingRecQty
				+ ", mixingReceiveRejectedQty=" + mixingReceiveRejectedQty + ", mixingIssueQty=" + mixingIssueQty
				+ ", mixingReturnQty=" + mixingReturnQty + ", mixingRejected=" + mixingRejected + ", prodIssueQty="
				+ prodIssueQty + ", prodReturnQty=" + prodReturnQty + ", prodRejectedQty=" + prodRejectedQty
				+ ", closingQty=" + closingQty + ", isDelStatus=" + isDelStatus + "]";
	}
	
	
	

}
