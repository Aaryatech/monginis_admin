package com.ats.adminpanel.model.materialrecreport;

 

public class GetMaterialRecieptReportMonthWise {
	
	 
	private int mrnId; 
	private String month; 
	private float basicValue; 
	private float discAmt; 
	private float other;
	private float freightAmt; 
	private float insuranceAmt; 
	private float cgst; 
	private float sgst; 
	private float igst; 
	private float cess;
	private float billAmount;
	
	
	
	public float getOther() {
		return other;
	}
	public void setOther(float other) {
		this.other = other;
	}
	public float getCess() {
		return cess;
	}
	public void setCess(float cess) {
		this.cess = cess;
	}
	public int getMrnId() {
		return mrnId;
	}
	public void setMrnId(int mrnId) {
		this.mrnId = mrnId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public float getBasicValue() {
		return basicValue;
	}
	public void setBasicValue(float basicValue) {
		this.basicValue = basicValue;
	}
	public float getDiscAmt() {
		return discAmt;
	}
	public void setDiscAmt(float discAmt) {
		this.discAmt = discAmt;
	}
	public float getFreightAmt() {
		return freightAmt;
	}
	public void setFreightAmt(float freightAmt) {
		this.freightAmt = freightAmt;
	}
	public float getInsuranceAmt() {
		return insuranceAmt;
	}
	public void setInsuranceAmt(float insuranceAmt) {
		this.insuranceAmt = insuranceAmt;
	}
	public float getCgst() {
		return cgst;
	}
	public void setCgst(float cgst) {
		this.cgst = cgst;
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
	public float getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}
	@Override
	public String toString() {
		return "GetMaterialRecieptReportMonthWise [mrnId=" + mrnId + ", month=" + month + ", basicValue=" + basicValue
				+ ", discAmt=" + discAmt + ", other=" + other + ", freightAmt=" + freightAmt + ", insuranceAmt="
				+ insuranceAmt + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", cess=" + cess
				+ ", billAmount=" + billAmount + "]";
	}

	
	
	
}
