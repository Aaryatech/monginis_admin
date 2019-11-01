package com.ats.adminpanel.model;

public class ItemWiseGrnGvnReport {
	
	private int itemId; 
	private String itemName; 
	private float aprQtyAcc; 
	private float grnGvnQty;
	private float aprTaxableAmt;
	private float aprTotalTax;
	private float aprGrandTotal;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public float getAprQtyAcc() {
		return aprQtyAcc;
	}
	public void setAprQtyAcc(float aprQtyAcc) {
		this.aprQtyAcc = aprQtyAcc;
	}
	public float getGrnGvnQty() {
		return grnGvnQty;
	}
	public void setGrnGvnQty(float grnGvnQty) {
		this.grnGvnQty = grnGvnQty;
	}
	public float getAprTaxableAmt() {
		return aprTaxableAmt;
	}
	public void setAprTaxableAmt(float aprTaxableAmt) {
		this.aprTaxableAmt = aprTaxableAmt;
	}
	public float getAprTotalTax() {
		return aprTotalTax;
	}
	public void setAprTotalTax(float aprTotalTax) {
		this.aprTotalTax = aprTotalTax;
	}
	public float getAprGrandTotal() {
		return aprGrandTotal;
	}
	public void setAprGrandTotal(float aprGrandTotal) {
		this.aprGrandTotal = aprGrandTotal;
	}
	@Override
	public String toString() {
		return "ItemWiseGrnGvnReport [itemId=" + itemId + ", itemName=" + itemName + ", aprQtyAcc=" + aprQtyAcc
				+ ", grnGvnQty=" + grnGvnQty + ", aprTaxableAmt=" + aprTaxableAmt + ", aprTotalTax=" + aprTotalTax
				+ ", aprGrandTotal=" + aprGrandTotal + "]";
	}
	
	

}
