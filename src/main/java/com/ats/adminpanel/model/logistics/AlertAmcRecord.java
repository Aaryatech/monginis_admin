package com.ats.adminpanel.model.logistics;
 

import com.fasterxml.jackson.annotation.JsonFormat;
 

public class AlertAmcRecord {
	
	private int amcId; 
	private int mechId; 
	private String mechName; 
	private int dealerId; 
	private String dealerName; 
	private int typeId; 
	private String amcFromDate; 
	private String amcToDate; 
	private String amcAlertDate; 
	private int remainingDay;
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
	public String getMechName() {
		return mechName;
	}
	public void setMechName(String mechName) {
		this.mechName = mechName;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getAmcFromDate() {
		return amcFromDate;
	}
	public void setAmcFromDate(String amcFromDate) {
		this.amcFromDate = amcFromDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getAmcToDate() {
		return amcToDate;
	}
	public void setAmcToDate(String amcToDate) {
		this.amcToDate = amcToDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getAmcAlertDate() {
		return amcAlertDate;
	}
	public void setAmcAlertDate(String amcAlertDate) {
		this.amcAlertDate = amcAlertDate;
	}
	public int getRemainingDay() {
		return remainingDay;
	}
	public void setRemainingDay(int remainingDay) {
		this.remainingDay = remainingDay;
	}
	@Override
	public String toString() {
		return "AlertAmcRecord [amcId=" + amcId + ", mechId=" + mechId + ", mechName=" + mechName + ", dealerId="
				+ dealerId + ", dealerName=" + dealerName + ", typeId=" + typeId + ", amcFromDate=" + amcFromDate
				+ ", amcToDate=" + amcToDate + ", amcAlertDate=" + amcAlertDate + ", remainingDay=" + remainingDay
				+ "]";
	}
	
	
	 
}
