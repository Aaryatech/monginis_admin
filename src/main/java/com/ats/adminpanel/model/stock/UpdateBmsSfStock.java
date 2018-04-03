package com.ats.adminpanel.model.stock;

public class UpdateBmsSfStock {

	private int bmsStockDeatilId;

	private int sfId;

	int bmsStockId;

	float prodIssueQty;

	float prodRejectedQty;

	float prodReturnQty;

	float mixingIssueQty;

	float mixingRejectedQty;

	float bmsClosingStock;

	public int getBmsStockDeatilId() {
		return bmsStockDeatilId;
	}

	public void setBmsStockDeatilId(int bmsStockDeatilId) {
		this.bmsStockDeatilId = bmsStockDeatilId;
	}

	public int getSfId() {
		return sfId;
	}

	public void setSfId(int sfId) {
		this.sfId = sfId;
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

	public float getBmsClosingStock() {
		return bmsClosingStock;
	}

	public void setBmsClosingStock(float bmsClosingStock) {
		this.bmsClosingStock = bmsClosingStock;
	}

	@Override
	public String toString() {
		return "UpdateBmsSfStock [bmsStockDeatilId=" + bmsStockDeatilId + ", sfId=" + sfId + ", bmsStockId="
				+ bmsStockId + ", prodIssueQty=" + prodIssueQty + ", prodRejectedQty=" + prodRejectedQty
				+ ", prodReturnQty=" + prodReturnQty + ", mixingIssueQty=" + mixingIssueQty + ", mixingRejectedQty="
				+ mixingRejectedQty + ", bmsClosingStock=" + bmsClosingStock + "]";
	}
	
}
