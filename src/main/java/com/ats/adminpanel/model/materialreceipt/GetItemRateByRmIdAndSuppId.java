package com.ats.adminpanel.model.materialreceipt;

 

public class GetItemRateByRmIdAndSuppId {
	
	private int rateId; 
	private int rmId; 
	private String rmName; 
	private float rate; 
	private int suppId;
	public int getRateId() {
		return rateId;
	}
	public void setRateId(int rateId) {
		this.rateId = rateId;
	}
	public int getRmId() {
		return rmId;
	}
	public void setRmId(int rmId) {
		this.rmId = rmId;
	}
	public String getRmName() {
		return rmName;
	}
	public void setRmName(String rmName) {
		this.rmName = rmName;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public int getSuppId() {
		return suppId;
	}
	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}
	@Override
	public String toString() {
		return "GetItemRateByRmIdAndSuppId [rateId=" + rateId + ", rmId=" + rmId + ", rmName=" + rmName + ", rate="
				+ rate + ", suppId=" + suppId + "]";
	}
	
	

}
