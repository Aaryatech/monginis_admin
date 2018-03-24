package com.ats.adminpanel.model.stock;

public class GetCurrentBmsSFStock {
	
	private int sfId;
	
	private String sfName;
	private int sfUomId;
	
	float prodIssueQty;
	float prodRejectedQty;
	float prodReturnQty;
	float mixingIssueQty;
	float mixingRejectedQty; 
	
	float bmsOpeningStock;
	
	float bmsClosingStock;

	public int getSfId() {
		return sfId;
	}

	public void setSfId(int sfId) {
		this.sfId = sfId;
	}

	public String getSfName() {
		return sfName;
	}

	public void setSfName(String sfName) {
		this.sfName = sfName;
	}

	public int getSfUomId() {
		return sfUomId;
	}

	public void setSfUomId(int sfUomId) {
		this.sfUomId = sfUomId;
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

	public float getBmsOpeningStock() {
		return bmsOpeningStock;
	}

	public void setBmsOpeningStock(float bmsOpeningStock) {
		this.bmsOpeningStock = bmsOpeningStock;
	}

	public float getBmsClosingStock() {
		return bmsClosingStock;
	}

	public void setBmsClosingStock(float bmsClosingStock) {
		this.bmsClosingStock = bmsClosingStock;
	}

	public float getProdRejectedQty() {
		return prodRejectedQty;
	}

	public void setProdRejectedQty(float prodRejectedQty) {
		this.prodRejectedQty = prodRejectedQty;
	}

	@Override
	public String toString() {
		return "GetCurrentBmsSFStock [sfId=" + sfId + ", sfName=" + sfName + ", sfUomId=" + sfUomId + ", prodIssueQty="
				+ prodIssueQty + ", prodRejectedQty=" + prodRejectedQty + ", prodReturnQty=" + prodReturnQty
				+ ", mixingIssueQty=" + mixingIssueQty + ", mixingRejectedQty=" + mixingRejectedQty
				+ ", bmsOpeningStock=" + bmsOpeningStock + ", bmsClosingStock=" + bmsClosingStock + "]";
	}


}
