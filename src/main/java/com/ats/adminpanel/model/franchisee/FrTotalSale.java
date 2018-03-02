package com.ats.adminpanel.model.franchisee;


public class FrTotalSale {
	
	private int month;

	private int frId;
	
	private float targetAmt;
	private float totalSale;

	public float getTargetAmt() {
		return targetAmt;
	}

	public void setTargetAmt(float targetAmt) {
		this.targetAmt = targetAmt;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public float getTotalSale() {
		return totalSale;
	}

	public void setTotalSale(float totalSale) {
		this.totalSale = totalSale;
	}

	@Override
	public String toString() {
		return "FrTotalSale [month=" + month + ", frId=" + frId + ", targetAmt=" + targetAmt + ", totalSale="
				+ totalSale + "]";
	}
    
}
