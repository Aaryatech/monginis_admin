package com.ats.adminpanel.model;

public class GetNonRegFr {

	private int frId;
	private String frName;
	private String frCode;
	private String taxableAmt;
	private String totalTax;
	private String grandTotal;
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getFrCode() {
		return frCode;
	}
	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}
	public String getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(String taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public String getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}
	public String getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}
	@Override
	public String toString() {
		return "GetNonRegFr [frId=" + frId + ", frName=" + frName + ", frCode=" + frCode + ", taxableAmt=" + taxableAmt
				+ ", totalTax=" + totalTax + ", grandTotal=" + grandTotal + "]";
	}
	
	
}
