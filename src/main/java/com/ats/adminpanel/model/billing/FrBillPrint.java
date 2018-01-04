package com.ats.adminpanel.model.billing;

import java.util.List;

public class FrBillPrint {
	
	public List<GetBillDetail> billDetailsList;
	
	int frId;
	int billNo;
	String frName;
	String frAddress;
	String invoiceNo;
	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public List<GetBillDetail> getBillDetailsList() {
		return billDetailsList;
	}
	public void setBillDetailsList(List<GetBillDetail> billDetailsList) {
		this.billDetailsList = billDetailsList;
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
	
	@Override
	public String toString() {
		return "FrBillPrint [billDetailsList=" + billDetailsList + ", "
					+ "frId=" + frId + ", "
					+ "billNo=" + billNo + ", "
					+ "frName="+ frName + ","
					+ " frAddress=" + frAddress + ","
					+ " invoiceNo=" + invoiceNo + "]";
	}

}
