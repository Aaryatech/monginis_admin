package com.ats.adminpanel.model.creditnote;


public class GetCreditNoteReport  {
	
	
	private int crnId;

	
	private String crnNo;

	private String crnDate;

	private int frId;

	private float crnTaxableAmt;

	private float crnTotalTax;

	private float crnGrandTotal;

	private String frName;

	private String frAddress;
	
	
	
	private String frGstNo;
	
	private int isSameState;

	private int isGrn;
	
	float sgstSum;
	float cgstSum;
	float igstSum;
	public int getCrnId() {
		return crnId;
	}
	public void setCrnId(int crnId) {
		this.crnId = crnId;
	}
	public String getCrnNo() {
		return crnNo;
	}
	public void setCrnNo(String crnNo) {
		this.crnNo = crnNo;
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
	public String getFrAddress() {
		return frAddress;
	}
	public void setFrAddress(String frAddress) {
		this.frAddress = frAddress;
	}
	public String getFrGstNo() {
		return frGstNo;
	}
	public void setFrGstNo(String frGstNo) {
		this.frGstNo = frGstNo;
	}
	public int getIsSameState() {
		return isSameState;
	}
	public void setIsSameState(int isSameState) {
		this.isSameState = isSameState;
	}
	public int getIsGrn() {
		return isGrn;
	}
	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}
	public float getSgstSum() {
		return sgstSum;
	}
	public void setSgstSum(float sgstSum) {
		this.sgstSum = sgstSum;
	}
	public float getCgstSum() {
		return cgstSum;
	}
	public void setCgstSum(float cgstSum) {
		this.cgstSum = cgstSum;
	}
	public float getIgstSum() {
		return igstSum;
	}
	public void setIgstSum(float igstSum) {
		this.igstSum = igstSum;
	}
	@Override
	public String toString() {
		return "GetCreditNoteReport [crnId=" + crnId + ", crnNo=" + crnNo + ", crnDate=" + crnDate + ", frId=" + frId
				+ ", crnTaxableAmt=" + crnTaxableAmt + ", crnTotalTax=" + crnTotalTax + ", crnGrandTotal="
				+ crnGrandTotal + ", frName=" + frName + ", frAddress=" + frAddress + ", frGstNo=" + frGstNo
				+ ", isSameState=" + isSameState + ", isGrn=" + isGrn + ", sgstSum=" + sgstSum + ", cgstSum=" + cgstSum
				+ ", igstSum=" + igstSum + "]";
	}
	
	
	

}
