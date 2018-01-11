package com.ats.adminpanel.model.billing;

import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;

// Bean to insert bill :


public class PostBillHeader {
	
	
	
	private int billNo;
		
	
	private int taxApplicable;
	
	
	private String invoiceNo;
	
	private Date billDate;
	
	
	private int frId;
	
	
	private String frCode;
	
	
	private float grandTotal;
	
	
	private float taxableAmt;
	
	
	private float totalTax;
	
	
	private int status;
	
	
	
	private int DelStatus;
	
	
	private String remark;
	
	private String time;
	
	private float sgstSum;
	private float cgstSum;
	private float igstSum;


	
	List<PostBillDetail> postBillDetailsList;
	

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

	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
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

	

	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDelStatus() {
		return DelStatus;
	}

	public void setDelStatus(int delStatus) {
		DelStatus = delStatus;
	}

	
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getTaxApplicable() {
		return taxApplicable;
	}

	public void setTaxApplicable(int taxApplicable) {
		this.taxApplicable = taxApplicable;
	}

	public List<PostBillDetail> getPostBillDetailsList() {
		return postBillDetailsList;
	}

	public void setPostBillDetailsList(List<PostBillDetail> postBillDetailsList) {
		this.postBillDetailsList = postBillDetailsList;
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

	@Override
	public String toString() {
		return "PostBillHeader [billNo=" + billNo + ", taxApplicable=" + taxApplicable + ", invoiceNo=" + invoiceNo
				+ ", billDate=" + billDate + ", frId=" + frId + ", frCode=" + frCode + ", grandTotal=" + grandTotal
				+ ", taxableAmt=" + taxableAmt + ", totalTax=" + totalTax + ", status=" + status + ", DelStatus="
				+ DelStatus + ", remark=" + remark + ", time=" + time + ", sgstSum=" + sgstSum + ", cgstSum=" + cgstSum
				+ ", igstSum=" + igstSum + ", postBillDetailsList=" + postBillDetailsList + "]";
	}

	
	

	
	
	
}
