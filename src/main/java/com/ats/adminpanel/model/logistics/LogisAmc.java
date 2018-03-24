package com.ats.adminpanel.model.logistics;
 
public class LogisAmc {
	
	 
	private int amcId;  
	private int mechId; 
	private int typeId; 
	private int dealerId; 
	private String amcFromDate; 
	private String amcToDate; 
	private int amcAlertFreq; 
	private String amcAlertDate; 
	private String billNo;  
	private float amcTaxableAmt;  
	private float amcTaxAmt; 
	private float amcTotal; 
	private int delStatus; 
	private String mechName; 
	private String dealerName;
	
	public int getAmcId() {
		return amcId;
	}
	public void setAmcId(int amcId) {
		this.amcId = amcId;
	}
	public int getMechId() {
		return mechId;
	}
	public void setMechId(int mechId) {
		this.mechId = mechId;
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
	public String getAmcFromDate() {
		return amcFromDate;
	}
	public void setAmcFromDate(String amcFromDate) {
		this.amcFromDate = amcFromDate;
	}
	public String getAmcToDate() {
		return amcToDate;
	}
	public void setAmcToDate(String amcToDate) {
		this.amcToDate = amcToDate;
	}
	public int getAmcAlertFreq() {
		return amcAlertFreq;
	}
	public void setAmcAlertFreq(int amcAlertFreq) {
		this.amcAlertFreq = amcAlertFreq;
	}
	public String getAmcAlertDate() {
		return amcAlertDate;
	}
	public void setAmcAlertDate(String amcAlertDate) {
		this.amcAlertDate = amcAlertDate;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public float getAmcTaxableAmt() {
		return amcTaxableAmt;
	}
	public void setAmcTaxableAmt(float amcTaxableAmt) {
		this.amcTaxableAmt = amcTaxableAmt;
	}
	public float getAmcTaxAmt() {
		return amcTaxAmt;
	}
	public void setAmcTaxAmt(float amcTaxAmt) {
		this.amcTaxAmt = amcTaxAmt;
	}
	public float getAmcTotal() {
		return amcTotal;
	}
	public void setAmcTotal(float amcTotal) {
		this.amcTotal = amcTotal;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	
	public String getMechName() {
		return mechName;
	}
	public void setMechName(String mechName) {
		this.mechName = mechName;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	@Override
	public String toString() {
		return "LogisAmc [amcId=" + amcId + ", mechId=" + mechId + ", typeId=" + typeId + ", dealerId=" + dealerId
				+ ", amcFromDate=" + amcFromDate + ", amcToDate=" + amcToDate + ", amcAlertFreq=" + amcAlertFreq
				+ ", amcAlertDate=" + amcAlertDate + ", billNo=" + billNo + ", amcTaxableAmt=" + amcTaxableAmt
				+ ", amcTaxAmt=" + amcTaxAmt + ", amcTotal=" + amcTotal + ", delStatus=" + delStatus + ", mechName="
				+ mechName + ", dealerName=" + dealerName + "]";
	}
	
	
	 

}
