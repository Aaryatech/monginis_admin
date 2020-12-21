package com.ats.adminpanel.model.billing;

import java.util.List;

import com.ats.adminpanel.model.MCategory;
import com.ats.adminpanel.model.franchisee.SubCategory;
import com.ats.adminpanel.model.item.MCategoryList;

public class FrBillPrint {
	
	public List<GetBillDetailPrint> billDetailsList;
	
	int frId;
	int billNo;
	String frName;
	String frAddress;
	String invoiceNo;
	int isSameState;
	String billDate;
	
	String amtInWords;
	
	float grandTotal;
	float tcsAmt;
	
	public List<SubCategory> subCatList;
	
	Company company;
	
	
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public List<GetBillDetailPrint> getBillDetailsList() {
		return billDetailsList;
	}
	public void setBillDetailsList(List<GetBillDetailPrint> billDetails) {
		this.billDetailsList = billDetails;
	}
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getFrAddress() {
		return frAddress;
	}
	public void setFrAddress(String frAddress) {
		this.frAddress = frAddress;
	}
	public int getIsSameState() {
		return isSameState;
	}
	public void setIsSameState(int isSameState) {
		this.isSameState = isSameState;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	
	
	public List<SubCategory> getSubCatList() {
		return subCatList;
	}
	public void setSubCatList(List<SubCategory> subCatList) {
		this.subCatList = subCatList;
	}
	public String getAmtInWords() {
		return amtInWords;
	}
	public void setAmtInWords(String amtInWords) {
		this.amtInWords = amtInWords;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	public float getTcsAmt() {
		return tcsAmt;
	}
	public void setTcsAmt(float tcsAmt) {
		this.tcsAmt = tcsAmt;
	}
	@Override
	public String toString() {
		return "FrBillPrint [billDetailsList=" + billDetailsList + ", frId=" + frId + ", billNo=" + billNo + ", frName="
				+ frName + ", frAddress=" + frAddress + ", invoiceNo=" + invoiceNo + ", isSameState=" + isSameState
				+ ", billDate=" + billDate + ", amtInWords=" + amtInWords + ", grandTotal=" + grandTotal + ", tcsAmt="
				+ tcsAmt + ", subCatList=" + subCatList + ", company=" + company + "]";
	}
	
}
