package com.ats.adminpanel.model.ggreports;

import java.util.Date;


 //report 2 Bean
public class GGReportGrpByFrId {
	
	
	
	private int frId;
	
	private int isGrn;
	
	float aprGrandTotal;
	float totalAmt;
	String frName;
	int reqQty;
	int aprQty ;
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
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
	public int getAprQty() {
		return aprQty;
	}
	public void setAprQty(int aprQty) {
		this.aprQty = aprQty;
	}
	@Override
	public String toString() {
		return "GGReportGrpByFrId [frId=" + frId + ", isGrn=" + isGrn + ", aprGrandTotal=" + aprGrandTotal
				+ ", totalAmt=" + totalAmt + ", frName=" + frName + ", reqQty=" + reqQty + ", aprQty=" + aprQty + "]";
	}

}
