package com.ats.adminpanel.model.stock;


public class UpdateBmsStock {
	
	private int bmsStockDeatilId;
	
	private int rmId;
	
	int bmsStockId;
	
	float prodIssueQty;
	
	float prodRejectedQty;
	
	float prodReturnQty;
	
	float mixingIssueQty;
	
	float mixingRejectedQty; 
	
	float mixingReturnQty;
	
	float storeIssueQty; 
	
	float storeRejectedQty;
	
	float bmsClosingStock;
	
	float mixingReceiveRejectedQty;
	
	float mixingRecQty;

	public int getBmsStockDeatilId() {
		return bmsStockDeatilId;
	}

	public void setBmsStockDeatilId(int bmsStockDeatilId) {
		this.bmsStockDeatilId = bmsStockDeatilId;
	}

	public int getRmId() {
		return rmId;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public int getBmsStockId() {
		return bmsStockId;
	}

	public void setBmsStockId(int bmsStockId) {
		this.bmsStockId = bmsStockId;
	}

	public float getProdIssueQty() {
		return prodIssueQty;
	}

	public void setProdIssueQty(float prodIssueQty) {
		this.prodIssueQty = prodIssueQty;
	}

	public float getProdRejectedQty() {
		return prodRejectedQty;
	}

	public void setProdRejectedQty(float prodRejectedQty) {
		this.prodRejectedQty = prodRejectedQty;
	}

	public float getProdReturnQty() {
		return prodReturnQty;
	}

	public void setProdReturnQty(float prodReturnQty) {
		this.prodReturnQty = prodReturnQty;
	}

	public float getMixingIssueQty() {
		return mixingIssueQty;
	}

	public void setMixingIssueQty(float mixingIssueQty) {
		this.mixingIssueQty = mixingIssueQty;
	}

	public float getMixingRejectedQty() {
		return mixingRejectedQty;
	}

	public void setMixingRejectedQty(float mixingRejectedQty) {
		this.mixingRejectedQty = mixingRejectedQty;
	}

	public float getMixingReturnQty() {
		return mixingReturnQty;
	}

	public void setMixingReturnQty(float mixingReturnQty) {
		this.mixingReturnQty = mixingReturnQty;
	}

	public float getStoreIssueQty() {
		return storeIssueQty;
	}

	public void setStoreIssueQty(float storeIssueQty) {
		this.storeIssueQty = storeIssueQty;
	}

	public float getStoreRejectedQty() {
		return storeRejectedQty;
	}

	public void setStoreRejectedQty(float storeRejectedQty) {
		this.storeRejectedQty = storeRejectedQty;
	}

	public float getBmsClosingStock() {
		return bmsClosingStock;
	}

	public void setBmsClosingStock(float bmsClosingStock) {
		this.bmsClosingStock = bmsClosingStock;
	}

	public float getMixingReceiveRejectedQty() {
		return mixingReceiveRejectedQty;
	}

	public void setMixingReceiveRejectedQty(float mixingReceiveRejectedQty) {
		this.mixingReceiveRejectedQty = mixingReceiveRejectedQty;
	}

	public float getMixingRecQty() {
		return mixingRecQty;
	}

	public void setMixingRecQty(float mixingRecQty) {
		this.mixingRecQty = mixingRecQty;
	}

	@Override
	public String toString() {
		return "UpdateBmsStock [bmsStockDeatilId=" + bmsStockDeatilId + ", rmId=" + rmId + ", bmsStockId=" + bmsStockId
				+ ", prodIssueQty=" + prodIssueQty + ", prodRejectedQty=" + prodRejectedQty + ", prodReturnQty="
				+ prodReturnQty + ", mixingIssueQty=" + mixingIssueQty + ", mixingRejectedQty=" + mixingRejectedQty
				+ ", mixingReturnQty=" + mixingReturnQty + ", storeIssueQty=" + storeIssueQty + ", storeRejectedQty="
				+ storeRejectedQty + ", bmsClosingStock=" + bmsClosingStock + ", mixingReceiveRejectedQty="
				+ mixingReceiveRejectedQty + ", mixingRecQty=" + mixingRecQty + "]";
	}

}
