package com.ats.adminpanel.model.ggreports;

import java.util.Date;

public class GGReportByDateAndFr {
	
	
	private int grnGvnHeaderId;

	private String grngvnSrno;

	private String grngvnDate;

	private int frId;

	private String frName;

	private int reqQty;
	
	private float totalAmt;
	
	
	private int aprQty;
	
	private float aprGrandTotal;
	
	int isGrn;

	public int getGrnGvnHeaderId() {
		return grnGvnHeaderId;
	}

	public void setGrnGvnHeaderId(int grnGvnHeaderId) {
		this.grnGvnHeaderId = grnGvnHeaderId;
	}

	public String getGrngvnSrno() {
		return grngvnSrno;
	}

	public void setGrngvnSrno(String grngvnSrno) {
		this.grngvnSrno = grngvnSrno;
	}

	public String getGrngvnDate() {
		return grngvnDate;
	}

	public void setGrngvnDate(String grngvnDate) {
		this.grngvnDate = grngvnDate;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public int getReqQty() {
		return reqQty;
	}

	public void setReqQty(int reqQty) {
		this.reqQty = reqQty;
	}

	public float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}

	public int getAprQty() {
		return aprQty;
	}

	public void setAprQty(int aprQty) {
		this.aprQty = aprQty;
	}

	public float getAprGrandTotal() {
		return aprGrandTotal;
	}

	public void setAprGrandTotal(float aprGrandTotal) {
		this.aprGrandTotal = aprGrandTotal;
	}

	public int getIsGrn() {
		return isGrn;
	}

	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}

	@Override
	public String toString() {
		return "GGReportByDateAndFr [grnGvnHeaderId=" + grnGvnHeaderId + ", grngvnSrno=" + grngvnSrno + ", grngvnDate="
				+ grngvnDate + ", frId=" + frId + ", frName=" + frName + ", reqQty=" + reqQty + ", totalAmt=" + totalAmt
				+ ", aprQty=" + aprQty + ", aprGrandTotal=" + aprGrandTotal + ", isGrn=" + isGrn + "]";
	}

	
	
}
