package com.ats.adminpanel.model.ggreports;

import java.util.Date;



public class GGReportGrpByMonthDate {

	private String grnGvnDate;
	
	String month;
	int isGrn;
	
	float aprGrandTotal;
	float totalAmt;
	
	int reqQty;
	int aprQty;
	
	public String getGrnGvnDate() {
		return grnGvnDate;
	}
	public void setGrnGvnDate(String grnGvnDate) {
		this.grnGvnDate = grnGvnDate;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getIsGrn() {
		return isGrn;
	}
	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}
	public float getAprGrandTotal() {
		return aprGrandTotal;
	}
	public void setAprGrandTotal(float aprGrandTotal) {
		this.aprGrandTotal = aprGrandTotal;
	}
	public float getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}
	public int getReqQty() {
		return reqQty;
	}
	public void setReqQty(int reqQty) {
		this.reqQty = reqQty;
	}
	public int getAprQty() {
		return aprQty;
	}
	public void setAprQty(int aprQty) {
		this.aprQty = aprQty;
	}
	
	@Override
	public String toString() {
		return "GGReportGrpByMonthDate [grnGvnDate=" + grnGvnDate + ", month=" + month + ", isGrn=" + isGrn
				+ ", aprGrandTotal=" + aprGrandTotal + ", totalAmt=" + totalAmt + ", reqQty=" + reqQty + ", aprQty="
				+ aprQty + "]";
	}
	
}
