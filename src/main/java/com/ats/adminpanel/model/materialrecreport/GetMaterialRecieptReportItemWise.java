package com.ats.adminpanel.model.materialrecreport;

 

public class GetMaterialRecieptReportItemWise {
	
	private String rmId; 
	private String rmName; 
	private int hsncdNo; 
	private float taxRate; 
	private float recdQty; 
	private float cgst; 
	private float sgst; 
	private float igst; 
	private float taxableAmt;
	public String getRmId() {
		return rmId;
	}
	public void setRmId(String rmId) {
		this.rmId = rmId;
	}
	public String getRmName() {
		return rmName;
	}
	public void setRmName(String rmName) {
		this.rmName = rmName;
	}
	public int getHsncdNo() {
		return hsncdNo;
	}
	public void setHsncdNo(int hsncdNo) {
		this.hsncdNo = hsncdNo;
	}
	public float getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}
	public float getRecdQty() {
		return recdQty;
	}
	public void setRecdQty(float recdQty) {
		this.recdQty = recdQty;
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
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	@Override
	public String toString() {
		return "GetMaterialRecieptReportItemWise [rmId=" + rmId + ", rmName=" + rmName + ", hsncdNo=" + hsncdNo
				+ ", taxRate=" + taxRate + ", recdQty=" + recdQty + ", cgst=" + cgst + ", sgst=" + sgst + ", igst="
				+ igst + ", taxableAmt=" + taxableAmt + "]";
	}
	
	

}
