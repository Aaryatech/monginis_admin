package com.ats.adminpanel.model;

public class NonRegFrReports {
	
	String frName;
	private int frCode;
	private Float taxableAmt;
	private Float totalTax;
	private Float grand_total;
	
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public int getFrCode() {
		return frCode;
	}
	public void setFrCode(int frCode) {
		this.frCode = frCode;
	}
	public Float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(Float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public Float getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(Float totalTax) {
		this.totalTax = totalTax;
	}
	public Float getGrand_total() {
		return grand_total;
	}
	public void setGrand_total(Float grand_total) {
		this.grand_total = grand_total;
	}
	@Override
	public String toString() {
		return "NonRegFrReports [frName=" + frName + ", frCode=" + frCode + ", taxableAmt=" + taxableAmt + ", totalTax="
				+ totalTax + ", grand_total=" + grand_total + "]";
	}
	
}
