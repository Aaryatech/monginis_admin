package com.ats.adminpanel.model.materialrecreport;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GetMaterialRecieptReportHsnCodeWise {
	
	private int mrnDetailId; 
	
	private String mrnNo; 
	private Date invBookDate; 
	private String invoiceNumber; 
	private String suppName; 
	private int hsncdNo;   
	private float value;
	private float cgst;
	private float sgst; 
	private float igst; 
	private float taxableAmt;
	
	
	
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public int getMrnDetailId() {
		return mrnDetailId;
	}
	public void setMrnDetailId(int mrnDetailId) {
		this.mrnDetailId = mrnDetailId;
	}
	public float getCgst() {
		return cgst;
	}
	public void setCgst(float cgst) {
		this.cgst = cgst;
	}
	public String getMrnNo() {
		return mrnNo;
	}
	public void setMrnNo(String mrnNo) {
		this.mrnNo = mrnNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getInvBookDate() {
		return invBookDate;
	}
	public void setInvBookDate(Date invBookDate) {
		this.invBookDate = invBookDate;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getSuppName() {
		return suppName;
	}
	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}
	public int getHsncdNo() {
		return hsncdNo;
	}
	public void setHsncdNo(int hsncdNo) {
		this.hsncdNo = hsncdNo;
	}
	public float getSgst() {
		return sgst;
	}
	public void setSgst(float sgst) {
		this.sgst = sgst;
	}
	public float getIgst() {
		return igst;
	}
	public void setIgst(float igst) {
		this.igst = igst;
	}
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	@Override
	public String toString() {
		return "GetMaterialRecieptReportHsnCodeWise [mrnDetailId=" + mrnDetailId + ", mrnNo=" + mrnNo + ", invBookDate="
				+ invBookDate + ", invoiceNumber=" + invoiceNumber + ", suppName=" + suppName + ", hsncdNo=" + hsncdNo
				+ ", value=" + value + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", taxableAmt="
				+ taxableAmt + "]";
	}
	
	

}
