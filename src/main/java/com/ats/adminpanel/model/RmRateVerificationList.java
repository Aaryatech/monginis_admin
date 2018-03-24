package com.ats.adminpanel.model; 

public class RmRateVerificationList { 
	
	private int rmRateVerId; 
	private int suppId; 
	private int rmId; 
	private String suppName; 
	private String rmName;
	public int getRmRateVerId() {
		return rmRateVerId;
	}
	public void setRmRateVerId(int rmRateVerId) {
		this.rmRateVerId = rmRateVerId;
	}
	public int getSuppId() {
		return suppId;
	}
	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}
	public int getRmId() {
		return rmId;
	}
	public void setRmId(int rmId) {
		this.rmId = rmId;
	}
	public String getSuppName() {
		return suppName;
	}
	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}
	public String getRmName() {
		return rmName;
	}
	public void setRmName(String rmName) {
		this.rmName = rmName;
	}
	@Override
	public String toString() {
		return "RmRateVerificationList [rmRateVerId=" + rmRateVerId + ", suppId=" + suppId + ", rmId=" + rmId
				+ ", suppName=" + suppName + ", rmName=" + rmName + "]";
	}
	
	
}
