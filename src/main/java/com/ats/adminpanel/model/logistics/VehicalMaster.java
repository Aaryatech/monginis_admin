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
	private int freqKIm; 
	private int wheelChangeFreq; 
	private int battaryChangeFreq; 
	private int acChangeFreq;
	
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
	
	
	public int getFreqKIm() {
		return freqKIm;
	}
	public void setFreqKIm(int freqKIm) {
		this.freqKIm = freqKIm;
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
	@Override
	public String toString() {
		return "VehicalMaster [vehId=" + vehId + ", vehNo=" + vehNo + ", makeId=" + makeId + ", vehEngNo=" + vehEngNo
				+ ", vehChesiNo=" + vehChesiNo + ", vehColor=" + vehColor + ", purchaseDate=" + purchaseDate
				+ ", regDate=" + regDate + ", dealerId=" + dealerId + ", fuelType=" + fuelType + ", vehTypeId="
				+ vehTypeId + ", variantId=" + variantId + ", vehCompAvg=" + vehCompAvg + ", vehStandAvg=" + vehStandAvg
				+ ", vehMiniAvg=" + vehMiniAvg + ", delStatus=" + delStatus + ", freqKIm=" + freqKIm
				+ ", wheelChangeFreq=" + wheelChangeFreq + ", battaryChangeFreq=" + battaryChangeFreq
				+ ", acChangeFreq=" + acChangeFreq + "]";
	}
	
	

}
