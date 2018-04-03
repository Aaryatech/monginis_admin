package com.ats.adminpanel.model.logistics;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
 

public class ServHeader {
	
	 
	private int servId;  
	private String servDate; 
	private int typeId; 
	private int dealerId;   
	private int servType; 
	private int servType2; 
	private String billNo; 
	private String billDate; 
	private int vehId;  
	private String vehNo; 
	private String servAdviseRem; 
	private String servDoneRem; 
	private float sprTot; 
	private float labChrge; 
	private float totalDisc; 
	private float totalExtra; 
	private float discOnBill; 
	private float extraOnBill; 
	private float taxAmt; 
	private float taxableAmt; 
	private float roundOff; 
	private float total; 
	private int servDoneKm; 
	private int nextDueKm; 
	private int isApproved; 
	private int delStatus; 
	private String billFile;
	List<ServDetail> servDetail;
	public int getServId() {
		return servId;
	}
	public void setServId(int servId) {
		this.servId = servId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getServDate() {
		return servDate;
	}
	public void setServDate(String servDate) {
		this.servDate = servDate;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public int getServType() {
		return servType;
	}
	public void setServType(int servType) {
		this.servType = servType;
	}
	public int getServType2() {
		return servType2;
	}
	public void setServType2(int servType2) {
		this.servType2 = servType2;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public int getVehId() {
		return vehId;
	}
	public void setVehId(int vehId) {
		this.vehId = vehId;
	}
	public String getVehNo() {
		return vehNo;
	}
	public void setVehNo(String vehNo) {
		this.vehNo = vehNo;
	}
	public String getServAdviseRem() {
		return servAdviseRem;
	}
	public void setServAdviseRem(String servAdviseRem) {
		this.servAdviseRem = servAdviseRem;
	}
	public String getServDoneRem() {
		return servDoneRem;
	}
	public void setServDoneRem(String servDoneRem) {
		this.servDoneRem = servDoneRem;
	}
	public float getSprTot() {
		return sprTot;
	}
	public void setSprTot(float sprTot) {
		this.sprTot = sprTot;
	}
	public float getLabChrge() {
		return labChrge;
	}
	public void setLabChrge(float labChrge) {
		this.labChrge = labChrge;
	}
	public float getTotalDisc() {
		return totalDisc;
	}
	public void setTotalDisc(float totalDisc) {
		this.totalDisc = totalDisc;
	}
	public float getTotalExtra() {
		return totalExtra;
	}
	public void setTotalExtra(float totalExtra) {
		this.totalExtra = totalExtra;
	}
	public float getDiscOnBill() {
		return discOnBill;
	}
	public void setDiscOnBill(float discOnBill) {
		this.discOnBill = discOnBill;
	}
	public float getExtraOnBill() {
		return extraOnBill;
	}
	public void setExtraOnBill(float extraOnBill) {
		this.extraOnBill = extraOnBill;
	}
	public float getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(float taxAmt) {
		this.taxAmt = taxAmt;
	}
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public float getRoundOff() {
		return roundOff;
	}
	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getServDoneKm() {
		return servDoneKm;
	}
	public void setServDoneKm(int servDoneKm) {
		this.servDoneKm = servDoneKm;
	}
	public int getNextDueKm() {
		return nextDueKm;
	}
	public void setNextDueKm(int nextDueKm) {
		this.nextDueKm = nextDueKm;
	}
	public int getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public List<ServDetail> getServDetail() {
		return servDetail;
	}
	public void setServDetail(List<ServDetail> servDetail) {
		this.servDetail = servDetail;
	}
	
	
	public String getBillFile() {
		return billFile;
	}
	public void setBillFile(String billFile) {
		this.billFile = billFile;
	}
	@Override
	public String toString() {
		return "ServHeader [servId=" + servId + ", servDate=" + servDate + ", typeId=" + typeId + ", dealerId="
				+ dealerId + ", servType=" + servType + ", servType2=" + servType2 + ", billNo=" + billNo
				+ ", billDate=" + billDate + ", vehId=" + vehId + ", vehNo=" + vehNo + ", servAdviseRem="
				+ servAdviseRem + ", servDoneRem=" + servDoneRem + ", sprTot=" + sprTot + ", labChrge=" + labChrge
				+ ", totalDisc=" + totalDisc + ", totalExtra=" + totalExtra + ", discOnBill=" + discOnBill
				+ ", extraOnBill=" + extraOnBill + ", taxAmt=" + taxAmt + ", taxableAmt=" + taxableAmt + ", roundOff="
				+ roundOff + ", total=" + total + ", servDoneKm=" + servDoneKm + ", nextDueKm=" + nextDueKm
				+ ", isApproved=" + isApproved + ", delStatus=" + delStatus + ", billFile=" + billFile + ", servDetail="
				+ servDetail + "]";
	}
	
	
	

}
