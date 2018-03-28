package com.ats.adminpanel.model.grngvn;

import java.util.Date;

public class GetBillsForFr {
	
	
	private int billNo;
	
	private String invoiceNo;
	
	private String billDate;

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

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	@Override
	public String toString() {
		return "GetBillsForFr [billNo=" + billNo + ", invoiceNo=" + invoiceNo + ", billDate=" + billDate + "]";
	}

	
	

}
