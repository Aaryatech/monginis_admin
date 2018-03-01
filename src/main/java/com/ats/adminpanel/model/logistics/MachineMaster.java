package com.ats.adminpanel.model.logistics;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MachineMaster {
	 
	private int machineId;  
	private String machineName; 
	private int makeId; 
	private int typeId; 
	private int variantId; 
	private int dealerId; 
	private String machineNo; 
	private String color; 
	private String variablePart; 
	private String purchaseDate;  
	private String registrationDate; 
	private int cleaningFrq; 
	private String lastCleaningDate; 
	private String nextCleaningDate; 
	private String nextAlertDate; 
	private int alertFreq; 
	private int delStatus;
	private String lastAmcDate; 
	private String nextAmcDate; 
	private String alertAmcDate;
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
	public int getMakeId() {
		return makeId;
	}
	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getVariantId() {
		return variantId;
	}
	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public String getMachineNo() {
		return machineNo;
	}
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getVariablePart() {
		return variablePart;
	}
	public void setVariablePart(String variablePart) {
		this.variablePart = variablePart;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public int getCleaningFrq() {
		return cleaningFrq;
	}
	public void setCleaningFrq(int cleaningFrq) {
		this.cleaningFrq = cleaningFrq;
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
	public int getAlertFreq() {
		return alertFreq;
	}
	public void setAlertFreq(int alertFreq) {
		this.alertFreq = alertFreq;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	
	public String getLastAmcDate() {
		return lastAmcDate;
	}
	public void setLastAmcDate(String lastAmcDate) {
		this.lastAmcDate = lastAmcDate;
	}
	public String getNextAmcDate() {
		return nextAmcDate;
	}
	public void setNextAmcDate(String nextAmcDate) {
		this.nextAmcDate = nextAmcDate;
	}
	public String getAlertAmcDate() {
		return alertAmcDate;
	}
	public void setAlertAmcDate(String alertAmcDate) {
		this.alertAmcDate = alertAmcDate;
	}
	@Override
	public String toString() {
		return "MachineMaster [machineId=" + machineId + ", machineName=" + machineName + ", makeId=" + makeId
				+ ", typeId=" + typeId + ", variantId=" + variantId + ", dealerId=" + dealerId + ", machineNo="
				+ machineNo + ", color=" + color + ", variablePart=" + variablePart + ", purchaseDate=" + purchaseDate
				+ ", registrationDate=" + registrationDate + ", cleaningFrq=" + cleaningFrq + ", lastCleaningDate="
				+ lastCleaningDate + ", nextCleaningDate=" + nextCleaningDate + ", nextAlertDate=" + nextAlertDate
				+ ", alertFreq=" + alertFreq + ", delStatus=" + delStatus + ", lastAmcDate=" + lastAmcDate
				+ ", nextAmcDate=" + nextAmcDate + ", alertAmcDate=" + alertAmcDate + "]";
	}
	
	

}
