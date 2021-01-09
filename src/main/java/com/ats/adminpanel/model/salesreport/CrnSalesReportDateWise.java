package com.ats.adminpanel.model.salesreport;

public class CrnSalesReportDateWise {

	private String uid;
	private String crnDate;
	private int frId;
	private float crnTaxableAmt;
	private float crnTotalTax;
	private float crnGrandTotal;
	private String frName;
	private String frCode;
	private String monthName;
	private int crnNo;
	private int isGrn;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCrnDate() {
		return crnDate;
	}
	public void setCrnDate(String crnDate) {
		this.crnDate = crnDate;
	}
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public float getCrnTaxableAmt() {
		return crnTaxableAmt;
	}
	public void setCrnTaxableAmt(float crnTaxableAmt) {
		this.crnTaxableAmt = crnTaxableAmt;
	}
	public float getCrnTotalTax() {
		return crnTotalTax;
	}
	public void setCrnTotalTax(float crnTotalTax) {
		this.crnTotalTax = crnTotalTax;
	}
	public float getCrnGrandTotal() {
		return crnGrandTotal;
	}
	public void setCrnGrandTotal(float crnGrandTotal) {
		this.crnGrandTotal = crnGrandTotal;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getFrCode() {
		return frCode;
	}
	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public int getCrnNo() {
		return crnNo;
	}
	public void setCrnNo(int crnNo) {
		this.crnNo = crnNo;
	}
	public int getIsGrn() {
		return isGrn;
	}
	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}
	@Override
	public String toString() {
		return "CrnSalesReportDateWise [uid=" + uid + ", crnDate=" + crnDate + ", frId=" + frId + ", crnTaxableAmt="
				+ crnTaxableAmt + ", crnTotalTax=" + crnTotalTax + ", crnGrandTotal=" + crnGrandTotal + ", frName="
				+ frName + ", frCode=" + frCode + ", monthName=" + monthName + ", crnNo=" + crnNo + ", isGrn=" + isGrn
				+ "]";
	}
	
}
