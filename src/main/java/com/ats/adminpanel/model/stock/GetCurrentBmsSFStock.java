package com.ats.adminpanel.model.stock;

public class GetCurrentBmsSFStock {
	
	private int sfId;
	
	private String sfName;
	private int sfUomId;
	
	float prod_issue_qty;
	float prod_rejected_qty;
	float prod_return_qty;
	float mixing_issue_qty;
	float mixing_rejected_qty; 
	
	float bms_opening_stock;
	
	float closingQty;
	

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

	public float getProd_issue_qty() {
		return prod_issue_qty;
	}

	public void setProd_issue_qty(float prod_issue_qty) {
		this.prod_issue_qty = prod_issue_qty;
	}

	public float getProd_rejected_qty() {
		return prod_rejected_qty;
	}

	public void setProd_rejected_qty(float prod_rejected_qty) {
		this.prod_rejected_qty = prod_rejected_qty;
	}

	public float getProd_return_qty() {
		return prod_return_qty;
	}

	public void setProd_return_qty(float prod_return_qty) {
		this.prod_return_qty = prod_return_qty;
	}

	public float getMixing_issue_qty() {
		return mixing_issue_qty;
	}

	public void setMixing_issue_qty(float mixing_issue_qty) {
		this.mixing_issue_qty = mixing_issue_qty;
	}

	public float getMixing_rejected_qty() {
		return mixing_rejected_qty;
	}

	public void setMixing_rejected_qty(float mixing_rejected_qty) {
		this.mixing_rejected_qty = mixing_rejected_qty;
	}

	public float getBms_opening_stock() {
		return bms_opening_stock;
	}

	public void setBms_opening_stock(float bms_opening_stock) {
		this.bms_opening_stock = bms_opening_stock;
	}

	public float getClosingQty() {
		return closingQty;
	}

	public void setClosingQty(float closingQty) {
		this.closingQty = closingQty;
	}

	@Override
	public String toString() {
		return "GetCurrentBmsSFStock [sfId=" + sfId + ", sfName=" + sfName + ", sfUomId=" + sfUomId
				+ ", prod_issue_qty=" + prod_issue_qty + ", prod_rejected_qty=" + prod_rejected_qty
				+ ", prod_return_qty=" + prod_return_qty + ", mixing_issue_qty=" + mixing_issue_qty
				+ ", mixing_rejected_qty=" + mixing_rejected_qty + ", bms_opening_stock=" + bms_opening_stock
				+ ", closingQty=" + closingQty + "]";
	}

	
}
