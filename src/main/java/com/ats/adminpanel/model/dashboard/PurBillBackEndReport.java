package com.ats.adminpanel.model.dashboard;


public class PurBillBackEndReport {
	
	private String  billDate;
	
	private float taxableAmt;
	private float totalTax;
	private float grandTotal;
	
	
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public float getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(float totalTax) {
		this.totalTax = totalTax;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	@Override
	public String toString() {
		return "PurBillBackEndReport [billDate=" + billDate + ", taxableAmt=" + taxableAmt + ", totalTax=" + totalTax
				+ ", grandTotal=" + grandTotal + "]";
	}

	
	
}
