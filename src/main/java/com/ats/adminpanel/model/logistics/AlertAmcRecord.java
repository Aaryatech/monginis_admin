package com.ats.adminpanel.model.logistics;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AlertAmcRecord {
	 
	private int id; 
	private String name; 
	private int dealerId; 
	private String dealerName; 
	private String lastAmcDate; 
	private String nextAmcDate; 
	private String alertAmcDate; 
	private int remainingDay;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getLastAmcDate() {
		return lastAmcDate;
	}
	public void setLastAmcDate(String lastAmcDate) {
		this.lastAmcDate = lastAmcDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getNextAmcDate() {
		return nextAmcDate;
	}
	public void setNextAmcDate(String nextAmcDate) {
		this.nextAmcDate = nextAmcDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getAlertAmcDate() {
		return alertAmcDate;
	}
	public void setAlertAmcDate(String alertAmcDate) {
		this.alertAmcDate = alertAmcDate;
	}
	public int getRemainingDay() {
		return remainingDay;
	}
	public void setRemainingDay(int remainingDay) {
		this.remainingDay = remainingDay;
	}
	@Override
	public String toString() {
		return "AlertAmcRecord [id=" + id + ", name=" + name + ", dealerId=" + dealerId + ", dealerName=" + dealerName
				+ ", lastAmcDate=" + lastAmcDate + ", nextAmcDate=" + nextAmcDate + ", alertAmcDate=" + alertAmcDate
				+ ", remainingDay=" + remainingDay + "]";
	}
	 
	
	 
}
