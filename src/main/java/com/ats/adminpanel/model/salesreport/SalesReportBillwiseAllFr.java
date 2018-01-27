package com.ats.adminpanel.model.salesreport;

import java.util.Date;


public class SalesReportBillwiseAllFr {
	
	
	private int billNo;
	
	private String invoiceNo;
	
	private String frName;
	
	private String billDate;
	
	private int frId;
	
	private String frCode;
	
	private String frCity;
	 
	private String frGstNo;
	 
	private int isSameState;
	 
	 
		private String itemHsncd;
		
		private float itemTax1;
		
		private float itemTax2;
		
		private float itemTax3;
	 
		private String itemName;
	 
	 float taxableAmtSum;
	 float sgstRsSum;
	 float cgstRsSum;
	 float igstRsSum;
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
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
	public String getItemHsncd() {
		return itemHsncd;
	}
	public void setItemHsncd(String itemHsncd) {
		this.itemHsncd = itemHsncd;
	}
	public float getItemTax1() {
		return itemTax1;
	}
	public void setItemTax1(float itemTax1) {
		this.itemTax1 = itemTax1;
	}
	public float getItemTax2() {
		return itemTax2;
	}
	public void setItemTax2(float itemTax2) {
		this.itemTax2 = itemTax2;
	}
	public float getItemTax3() {
		return itemTax3;
	}
	public void setItemTax3(float itemTax3) {
		this.itemTax3 = itemTax3;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public float getTaxableAmtSum() {
		return taxableAmtSum;
	}
	public void setTaxableAmtSum(float taxableAmtSum) {
		this.taxableAmtSum = taxableAmtSum;
	}
	public float getSgstRsSum() {
		return sgstRsSum;
	}
	public void setSgstRsSum(float sgstRsSum) {
		this.sgstRsSum = sgstRsSum;
	}
	public float getCgstRsSum() {
		return cgstRsSum;
	}
	public void setCgstRsSum(float cgstRsSum) {
		this.cgstRsSum = cgstRsSum;
	}
	public float getIgstRsSum() {
		return igstRsSum;
	}
	public void setIgstRsSum(float igstRsSum) {
		this.igstRsSum = igstRsSum;
	}
	@Override
	public String toString() {
		return "SalesReportBillwiseAllFr [billNo=" + billNo + ", invoiceNo=" + invoiceNo + ", frName=" + frName
				+ ", billDate=" + billDate + ", frId=" + frId + ", frCode=" + frCode + ", frCity=" + frCity
				+ ", frGstNo=" + frGstNo + ", isSameState=" + isSameState + ", itemHsncd=" + itemHsncd + ", itemTax1="
				+ itemTax1 + ", itemTax2=" + itemTax2 + ", itemTax3=" + itemTax3 + ", itemName=" + itemName
				+ ", taxableAmtSum=" + taxableAmtSum + ", sgstRsSum=" + sgstRsSum + ", cgstRsSum=" + cgstRsSum
				+ ", igstRsSum=" + igstRsSum + "]";
	}
	 
	 

}
