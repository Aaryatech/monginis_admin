package com.ats.adminpanel.model.logistics;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AlertMachineServicingRecord {
	 
	private int machineId; 
	private String machineName; 
	private int dealer_id; 
	private String dealerName; 
	private String lastCleaningDate; 
	private String nextCleaningDate; 
	private String nextAlertDate; 
	private int remainingDay;
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
	public int getDealer_id() {
		return dealer_id;
	}
	public void setDealer_id(int dealer_id) {
		this.dealer_id = dealer_id;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getLastCleaningDate() {
		return lastCleaningDate;
	}
	public void setLastCleaningDate(String lastCleaningDate) {
		this.lastCleaningDate = lastCleaningDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getNextCleaningDate() {
		return nextCleaningDate;
	}
	public void setNextCleaningDate(String nextCleaningDate) {
		this.nextCleaningDate = nextCleaningDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getNextAlertDate() {
		return nextAlertDate;
	}
	public void setNextAlertDate(String nextAlertDate) {
		this.nextAlertDate = nextAlertDate;
	}
	public int getRemainingDay() {
		return remainingDay;
	}
	public void setRemainingDay(int remainingDay) {
		this.remainingDay = remainingDay;
	}
	@Override
	public String toString() {
		return "AlertMachineServicingRecord [machineId=" + machineId + ", machineName=" + machineName + ", dealer_id="
				+ dealer_id + ", dealerName=" + dealerName + ", lastCleaningDate=" + lastCleaningDate
				+ ", nextCleaningDate=" + nextCleaningDate + ", nextAlertDate=" + nextAlertDate + ", remainingDay="
				+ remainingDay + "]";
	}
	
	

}
