package com.ats.adminpanel.model.franchisee;

import java.io.Serializable;

public class FrTotalSale {
	
	private int month;

	private int frId;
	
	private float totalSale;

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
		return "FrTotalSale [month=" + month + ", frId=" + frId + ", totalSale=" + totalSale + "]";
	}
	
	
}
