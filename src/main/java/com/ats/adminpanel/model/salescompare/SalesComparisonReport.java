package com.ats.adminpanel.model.salescompare;


public class SalesComparisonReport {

	private int frId;
	
	private	int month;
	
	private	String monthName;
	
	private	String routeName;
	
	private	String frName;
	
	private	int frRouteId;
	
	private	float billTotal;
	
	private	float grnAmt;
	
	

	public float getGrnAmt() {
		return grnAmt;
	}

	public void setGrnAmt(float grnAmt) {
		this.grnAmt = grnAmt;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}


	public int getFrRouteId() {
		return frRouteId;
	}

	public void setFrRouteId(int frRouteId) {
		this.frRouteId = frRouteId;
	}

	public float getBillTotal() {
		return billTotal;
	}

	public void setBillTotal(float billTotal) {
		this.billTotal = billTotal;
	}

	@Override
	public String toString() {
		return "SalesConsumeReport [frId=" + frId + ", month=" + month + ", monthName=" + monthName + ", routeName="
				+ routeName + ", frName=" + frName + ", frRouteId=" + frRouteId + ", billTotal=" + billTotal + "]";
	}
	
}
