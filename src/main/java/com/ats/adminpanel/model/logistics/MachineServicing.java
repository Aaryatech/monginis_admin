package com.ats.adminpanel.model.logistics;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
 

public class MachineServicing {
	 
	 
	private int mechServId;  
	private int machineId; 
	private String machineName; 
	private String prsnName; 
	private int paidType; 
	private int dealerId; 
	private String dealerName; 
	private String date; 
	private String nextServDate; 
	private String alertServDate; 
	private int isAlertRequired; 
	private int isApproved; 
	private int delStatus;
	private int servType;
	public int getMechServId() {
		return mechServId;
	}
	public void setMechServId(int mechServId) {
		this.mechServId = mechServId;
	}
	public int getMachineId() {
		return machineId;
	}
	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getPrsnName() {
		return prsnName;
	}
	public void setPrsnName(String prsnName) {
		this.prsnName = prsnName;
	}
	public int getPaidType() {
		return paidType;
	}
	public void setPaidType(int paidType) {
		this.paidType = paidType;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getNextServDate() {
		return nextServDate;
	}
	public void setNextServDate(String nextServDate) {
		this.nextServDate = nextServDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getAlertServDate() {
		return alertServDate;
	}
	public void setAlertServDate(String alertServDate) {
		this.alertServDate = alertServDate;
	}
	public int getIsAlertRequired() {
		return isAlertRequired;
	}
	public void setIsAlertRequired(int isAlertRequired) {
		this.isAlertRequired = isAlertRequired;
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
	
	
	public int getServType() {
		return servType;
	}
	public void setServType(int servType) {
		this.servType = servType;
	}
	@Override
	public String toString() {
		return "MachineServicing [mechServId=" + mechServId + ", machineId=" + machineId + ", machineName="
				+ machineName + ", prsnName=" + prsnName + ", paidType=" + paidType + ", dealerId=" + dealerId
				+ ", dealerName=" + dealerName + ", date=" + date + ", nextServDate=" + nextServDate
				+ ", alertServDate=" + alertServDate + ", isAlertRequired=" + isAlertRequired + ", isApproved="
				+ isApproved + ", delStatus=" + delStatus + ", servType=" + servType + "]";
	}
	
	

}
