package com.ats.adminpanel.model.salesreport;

import java.util.Date;

public class SalesReportBillwise {
	
	private int billNo;
	
	private int taxApplicable;
	
	private String invoiceNo;
	
	private String billDate;
	
	private String frName;
	
	private int frId;
	
	private String frCode;
	
	private float grandTotal;
	
	private float taxableAmt;
	
	private float totalTax;

	private float sgstSum;
	
	private float cgstSum;
	
	private float igstSum;
	
	private float roundOff;
	
	private String frCity;
	 
	private String frGstNo;
	 
	private int isSameState;
	
	String month;
	

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public int getTaxApplicable() {
		return taxApplicable;
	}

	public void setTaxApplicable(int taxApplicable) {
		this.taxApplicable = taxApplicable;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getFrCode() {
		return frCode;
	}

	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
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

	public float getSgstSum() {
		return sgstSum;
	}

	public void setSgstSum(float sgstSum) {
		this.sgstSum = sgstSum;
	}

	public float getCgstSum() {
		return cgstSum;
	}

	public void setCgstSum(float cgstSum) {
		this.cgstSum = cgstSum;
	}

	public float getIgstSum() {
		return igstSum;
	}

	public void setIgstSum(float igstSum) {
		this.igstSum = igstSum;
	}

	public float getRoundOff() {
		return roundOff;
	}

	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
	}

	public String getFrCity() {
		return frCity;
	}

	public void setFrCity(String frCity) {
		this.frCity = frCity;
	}

	public String getFrGstNo() {
		return frGstNo;
	}

	public void setFrGstNo(String frGstNo) {
		this.frGstNo = frGstNo;
	}

	public int getIsSameState() {
		return isSameState;
	}

	public void setIsSameState(int isSameState) {
		this.isSameState = isSameState;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "SalesReportBillwise [billNo=" + billNo + ", taxApplicable=" + taxApplicable + ", invoiceNo=" + invoiceNo
				+ ", billDate=" + billDate + ", frName=" + frName + ", frId=" + frId + ", frCode=" + frCode
				+ ", grandTotal=" + grandTotal + ", taxableAmt=" + taxableAmt + ", totalTax=" + totalTax + ", sgstSum="
				+ sgstSum + ", cgstSum=" + cgstSum + ", igstSum=" + igstSum + ", roundOff=" + roundOff + ", frCity="
				+ frCity + ", frGstNo=" + frGstNo + ", isSameState=" + isSameState + ", month=" + month + "]";
	}

}
