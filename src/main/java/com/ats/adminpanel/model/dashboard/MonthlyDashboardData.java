package com.ats.adminpanel.model.dashboard;

public class MonthlyDashboardData {

	private long salesAmt;
	
	private float grnAmt1;
	
	private float grnAmt2;
	
	private float grnAmt3;
	
	private float grnTotalAmt;
	
	private float gvnAmt;
	
	private long netSaleAmt;
	
	private int month;

	private int year;

	public long getSalesAmt() {
		return salesAmt;
	}

	public float getGrnAmt1() {
		return grnAmt1;
	}

	public float getGrnAmt2() {
		return grnAmt2;
	}

	public float getGrnAmt3() {
		return grnAmt3;
	}

	public float getGrnTotalAmt() {
		return grnTotalAmt;
	}

	public double getGvnAmt() {
		return gvnAmt;
	}

	public long getNetSaleAmt() {
		return netSaleAmt;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public void setSalesAmt(long salesAmt) {
		this.salesAmt = salesAmt;
	}

	public void setGrnAmt1(float grnAmt1) {
		this.grnAmt1 = grnAmt1;
	}

	public void setGrnAmt2(float grnAmt2) {
		this.grnAmt2 = grnAmt2;
	}

	public void setGrnAmt3(float grnAmt3) {
		this.grnAmt3 = grnAmt3;
	}

	public void setGrnTotalAmt(float grnTotalAmt) {
		this.grnTotalAmt = grnTotalAmt;
	}

	public void setGvnAmt(float gvnAmt) {
		this.gvnAmt = gvnAmt;
	}

	public void setNetSaleAmt(long netSaleAmt) {
		this.netSaleAmt = netSaleAmt;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "MonthlyDashboardData [salesAmt=" + salesAmt + ", grnAmt1=" + grnAmt1 + ", grnAmt2=" + grnAmt2
				+ ", grnAmt3=" + grnAmt3 + ", grnTotalAmt=" + grnTotalAmt + ", gvnAmt=" + gvnAmt + ", netSaleAmt="
				+ netSaleAmt + ", month=" + month + ", year=" + year + "]";
	}

	
	
	
	
	
}
