package com.ats.adminpanel.model.logistics;

 
  

import com.fasterxml.jackson.annotation.JsonFormat; 
public class VehicalMaster {
	
	 
	private int vehId;  
	private String vehNo; 
	private int makeId; 
	private String vehEngNo; 
	private String vehChesiNo; 
	private String vehColor; 
	private String purchaseDate; 
	private String regDate; 
	private int dealerId; 
	private int fuelType; 
	private int vehTypeId; 
	private int variantId; 
	private float vehCompAvg; 
	private float vehStandAvg; 
	private float vehMiniAvg; 
	private int delStatus; 
	private int freqKm; 
	private int wheelChangeFreq; 
	private int battaryChangeFreq; 
	private int acChangeFreq; 
	private int currentRunningKm; 
	private int lastServicingKm; 
	private int nextServicingKm; 
	private int alertNextServicingKm; 
	private String lastAmcDate; 
	private String nextAmcDate; 
	private String alertAmcDate;
	
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
	public int getMakeId() {
		return makeId;
	}
	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}
	public String getVehEngNo() {
		return vehEngNo;
	}
	public void setVehEngNo(String vehEngNo) {
		this.vehEngNo = vehEngNo;
	}
	public String getVehChesiNo() {
		return vehChesiNo;
	}
	public void setVehChesiNo(String vehChesiNo) {
		this.vehChesiNo = vehChesiNo;
	}
	public String getVehColor() {
		return vehColor;
	}
	public void setVehColor(String vehColor) {
		this.vehColor = vehColor;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public int getFuelType() {
		return fuelType;
	}
	public void setFuelType(int fuelType) {
		this.fuelType = fuelType;
	}
	public int getVehTypeId() {
		return vehTypeId;
	}
	public void setVehTypeId(int vehTypeId) {
		this.vehTypeId = vehTypeId;
	}
	public int getVariantId() {
		return variantId;
	}
	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}
	public float getVehCompAvg() {
		return vehCompAvg;
	}
	public void setVehCompAvg(float vehCompAvg) {
		this.vehCompAvg = vehCompAvg;
	}
	public float getVehStandAvg() {
		return vehStandAvg;
	}
	public void setVehStandAvg(float vehStandAvg) {
		this.vehStandAvg = vehStandAvg;
	}
	public float getVehMiniAvg() {
		return vehMiniAvg;
	}
	public void setVehMiniAvg(float vehMiniAvg) {
		this.vehMiniAvg = vehMiniAvg;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	
	 
	public int getFreqKm() {
		return freqKm;
	}
	public void setFreqKm(int freqKm) {
		this.freqKm = freqKm;
	}
	public int getWheelChangeFreq() {
		return wheelChangeFreq;
	}
	public void setWheelChangeFreq(int wheelChangeFreq) {
		this.wheelChangeFreq = wheelChangeFreq;
	}
	public int getBattaryChangeFreq() {
		return battaryChangeFreq;
	}
	public void setBattaryChangeFreq(int battaryChangeFreq) {
		this.battaryChangeFreq = battaryChangeFreq;
	}
	public int getAcChangeFreq() {
		return acChangeFreq;
	}
	public void setAcChangeFreq(int acChangeFreq) {
		this.acChangeFreq = acChangeFreq;
	}
	
	
	
	public int getCurrentRunningKm() {
		return currentRunningKm;
	}
	public void setCurrentRunningKm(int currentRunningKm) {
		this.currentRunningKm = currentRunningKm;
	}
	public int getLastServicingKm() {
		return lastServicingKm;
	}
	public void setLastServicingKm(int lastServicingKm) {
		this.lastServicingKm = lastServicingKm;
	}
	public int getNextServicingKm() {
		return nextServicingKm;
	}
	public void setNextServicingKm(int nextServicingKm) {
		this.nextServicingKm = nextServicingKm;
	}
	public int getAlertNextServicingKm() {
		return alertNextServicingKm;
	}
	public void setAlertNextServicingKm(int alertNextServicingKm) {
		this.alertNextServicingKm = alertNextServicingKm;
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
	@Override
	public String toString() {
		return "VehicalMaster [vehId=" + vehId + ", vehNo=" + vehNo + ", makeId=" + makeId + ", vehEngNo=" + vehEngNo
				+ ", vehChesiNo=" + vehChesiNo + ", vehColor=" + vehColor + ", purchaseDate=" + purchaseDate
				+ ", regDate=" + regDate + ", dealerId=" + dealerId + ", fuelType=" + fuelType + ", vehTypeId="
				+ vehTypeId + ", variantId=" + variantId + ", vehCompAvg=" + vehCompAvg + ", vehStandAvg=" + vehStandAvg
				+ ", vehMiniAvg=" + vehMiniAvg + ", delStatus=" + delStatus + ", freqKm=" + freqKm
				+ ", wheelChangeFreq=" + wheelChangeFreq + ", battaryChangeFreq=" + battaryChangeFreq
				+ ", acChangeFreq=" + acChangeFreq + ", currentRunningKm=" + currentRunningKm + ", lastServicingKm="
				+ lastServicingKm + ", nextServicingKm=" + nextServicingKm + ", alertNextServicingKm="
				+ alertNextServicingKm + ", lastAmcDate=" + lastAmcDate + ", nextAmcDate=" + nextAmcDate
				+ ", alertAmcDate=" + alertAmcDate + "]";
	}
	
	

}
